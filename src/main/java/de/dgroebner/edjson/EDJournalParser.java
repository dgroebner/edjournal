package de.dgroebner.edjson;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.tools.generic.DisplayTool;
import org.apache.velocity.tools.generic.NumberTool;
import org.json.JSONObject;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;

import de.dgroebner.edjson.db.Combatlog;
import de.dgroebner.edjson.db.Faction;
import de.dgroebner.edjson.db.Financedata;
import de.dgroebner.edjson.db.Journal;
import de.dgroebner.edjson.db.JournalFile;
import de.dgroebner.edjson.db.Material;
import de.dgroebner.edjson.db.Mission;
import de.dgroebner.edjson.db.Navlog;
import de.dgroebner.edjson.db.Planet;
import de.dgroebner.edjson.db.PlanetMaterial;
import de.dgroebner.edjson.db.RingTable;
import de.dgroebner.edjson.db.Ship;
import de.dgroebner.edjson.db.Star;
import de.dgroebner.edjson.db.Starport;
import de.dgroebner.edjson.db.Starsystem;
import de.dgroebner.edjson.db.model.DBFaction;
import de.dgroebner.edjson.db.model.DBStarsystem;
import de.dgroebner.edjson.db.model.VCombatlog;
import de.dgroebner.edjson.db.model.VMaterialSummary;
import de.dgroebner.edjson.db.model.VPlanet;
import de.dgroebner.edjson.db.model.VStar;
import de.dgroebner.edjson.db.model.VStarportLog;
import de.dgroebner.edjson.model.EDJournalEvents;
import de.dgroebner.edjson.model.JournalModel;
import de.dgroebner.edjson.velocity.LocalDateTimeTool;

/**
 * Parser für das Elite Dangerous Journal
 * 
 * @author dgroebner
 */
public class EDJournalParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(EDJournalParser.class);

    private final File file;

    private final DBI dbi = new DBI("jdbc:jtds:sqlserver://localhost:1433", "edjournal", "edjournal");

    /**
     * Constructor.
     *
     * @param file {@link File} zu parsendes Journalfile
     */
    public EDJournalParser(final File file) {
        this.file = file;
    }

    /**
     * Führt Zeilen mit Zeilenumbruch zusammen
     * 
     * @param rawLines {@link List} Dateizeilen aus der Datei
     * @return {@link List} bereinigte Liste der Dateizeilen
     */
    private List<String> normalizeFileLines(final List<String> rawLines) {
        final List<String> cleanedLines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();
        for (final String rawLine : rawLines) {
            if (StringUtils.startsWith(rawLine, "{") && StringUtils.endsWith(rawLine, "}")) {
                cleanedLines.add(rawLine);
            } else if (StringUtils.startsWith(rawLine, "{")) {
                currentLine = new StringBuilder(rawLine);
            } else if (StringUtils.endsWith(rawLine, "}")) {
                currentLine.append(" ").append(rawLine);
                cleanedLines.add(currentLine.toString());
            } else {
                currentLine.append(" ").append(rawLine);
            }
        }

        return cleanedLines;
    }

    /**
     * Verarbeitet die Dateien
     * 
     * @throws IOException IOException
     */
    private void doAction() throws IOException {
        final JournalFile fileTable = new JournalFile(dbi, file);
        if (fileTable.isFileAlreadyPared()) {
            return;
        }
        final int id = fileTable.save();
        normalizeFileLines(FileUtils.readLines(file, "UTF-8")).forEach(line -> parseLine(line, id));
    }

    /**
     * Parst die Dateizeile
     * 
     * @param fileline {@link String}
     * @param fileId int id des Dateieintrags in der Datenbank
     */
    private void parseLine(final String fileline, final int fileId) {
        final JSONObject obj = new JSONObject(fileline);

        final EDJournalEvents event = EDJournalEvents.forCode(obj.getString("event"));
        final JournalModel model = event.getModel(obj);
        final int journalId = event.getAction().writeJournalToDatabase(dbi, fileId, model);
        event.getAction().doActionOn(dbi, journalId, model);

        LOGGER.info("{} : {} : {}", file.getAbsolutePath(), obj.getString("timestamp"), obj.get("event"));
    }

    /**
     * Main Methode<br>
     * <br>
     * Erwartet den Pfad zu den parsenden Dateien als Argument
     * 
     * @param args Array of {@link String}
     */
    public static void main(final String[] args) {
        if (Arrays.stream(args).count() < 1) {
            return;
        }
        final File dir = new File(Arrays.stream(args).findFirst().get());
        LOGGER.info("Suche Journaldateien im Ordner: {}", dir);
        final Collection<File> files = FileUtils.listFiles(dir, new WildcardFileFilter("*.log"), null);
        LOGGER.info("{} Dateien gefunden", Integer.toString(files.size()));
        files.forEach(EDJournalParser::parse);
        final EDJournalParser parser = new EDJournalParser(dir);
        parser.createReports();
    }

    private void createReports() {
        final Properties props = new Properties();
        props.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        props.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        Velocity.init(props);
        final VelocityContext context = new VelocityContext();

        final Ship shipDao = new Ship(dbi);
        final Starsystem systemDao = new Starsystem(dbi);
        final Combatlog combatLogDao = new Combatlog(dbi);
        context.put("datetool", new LocalDateTimeTool());
        context.put("numbertool", new NumberTool());
        context.put("stringUtils", new StringUtils());
        context.put("journalList", new Journal(dbi).listJournals());
        context.put("currentShip", shipDao.getCurrentShip());
        context.put("shipList", shipDao.listShipSummary());
        context.put("currentSystem", systemDao.getCurrentSystem());
        context.put("starsystemLogList", systemDao.listTopSystems());
        context.put("starportLogList", new Starport(dbi).listStarportLog());
        context.put("currentCredits", Integer.valueOf(new Financedata(dbi).getSaldo()));
        context.put("navLogList", new Navlog(dbi).getNavlog());
        context.put("missionList", new Mission(dbi).getMissionLog());
        context.put("financeLogList", new Financedata(dbi).listFinanceLog());
        context.put("materials", new Material(dbi).list());
        final List<VCombatlog> combatLogList = combatLogDao.list();
        context.put("combatLogList", combatLogList);
        context.put("combatLogOverview", aggregateCombatLog(combatLogList));

        final Template journalTemplate = Velocity.getTemplate("templates/journalTemplate.vm");
        writeToFile(context, journalTemplate, "journal.html");

        final RingTable ringDao = new RingTable(dbi);
        final Planet planetDao = new Planet(dbi);
        final PlanetMaterial planetMaterialDao = new PlanetMaterial(dbi);
        context.put("countStars", Integer.valueOf(new Star(dbi).count()));
        context.put("countPlanets", Integer.valueOf(planetDao.count()));
        context.put("countByPlanet", Integer.valueOf(planetMaterialDao.countByPlanet()));
        context.put("countRings", Integer.valueOf(ringDao.count()));
        context.put("materialSummary", planetMaterialDao.listMaterialSummary());
        context.put("ringList", ringDao.list());
        context.put("materialList", planetMaterialDao.listMaterialComplete());
        context.put("starList", new Star(dbi).list());
        context.put("planetList", planetDao.list());
        final Template planetTemplate = Velocity.getTemplate("templates/planetTemplate.vm");
        writeToFile(context, planetTemplate, "starobjects.html");

        writeStarsystemWikipage("HIP 67086");
    }

    private void writeStarsystemWikipage(final String name) {
        final VelocityContext context = new VelocityContext();
        final DBStarsystem system = new Starsystem(dbi).getByName(name);
        final DBFaction systemFaction = new Faction(dbi).getById(system.getFactionId());
        final List<VStar> starList = new Star(dbi).listByStarsystemId(Integer.valueOf(system.getId()));
        final List<VPlanet> planetList = new Planet(dbi).listByStarsystemId(Integer.valueOf(system.getId()));
        final List<VMaterialSummary> materialList = new ArrayList<>();
        final PlanetMaterial planMatDao = new PlanetMaterial(dbi);
        final List<VStarportLog> stationList = new Starport(dbi).list(system.getName());
        planetList.forEach(planet -> materialList.addAll(planMatDao.listMaterialByPlanet(planet.getPlanetname())));
        context.put("displayTool", new DisplayTool());
        context.put("numberTool", new NumberTool());
        context.put("stringUtils", new StringUtils());
        context.put("system", system);
        context.put("systemFaction", systemFaction);
        context.put("starList", starList);
        context.put("firststar", starList.stream().findFirst().get());
        context.put("planetList", planetList);
        context.put("materialList", materialList);
        context.put("stationList", stationList);

        final Template wikiStarsystemTemplate = Velocity.getTemplate("templates/wiki.starsystem.vm", "UTF-8");
        writeToFile(context, wikiStarsystemTemplate, name + ".txt");
    }

    /**
     * Generiert eine Überblicks-Map über das Kampflog und gibt diese zurück
     * 
     * @param combatLogList {@link List} von {@link VCombatlog}
     * @return {@link Map} von {@link StringUtils} und {@link Integer}
     */
    private Map<String, Integer> aggregateCombatLog(final List<VCombatlog> combatLogList) {
        final Map<String, Integer> overview = new HashMap<>();
        combatLogList.stream().filter(log -> Combatlog.ACTION.ENEMY_KILLED.name().equals(log.getAction()))
                .forEach(log -> aggregateCombatOverview(log, overview));

        final List<Map.Entry<String, Integer>> list = Lists.newArrayList(overview.entrySet());
        Collections.sort(list, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));
        Collections.reverse(list);

        final LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        list.forEach(entry -> result.put(entry.getKey(), entry.getValue()));
        return result;
    }

    /**
     * Aggregiert die Kontakte je Fraktion
     * 
     * @param log {@link VCombatlog}
     * @param overview {@link Map} von {@link StringUtils} und {@link Integer}
     */
    private void aggregateCombatOverview(final VCombatlog log, final Map<String, Integer> overview) {
        if (overview.containsKey(log.getFactionName())) {
            final int currentCount = overview.get(log.getFactionName()).intValue();
            overview.put(log.getFactionName(), Integer.valueOf(currentCount + 1));
        } else {
            overview.put(log.getFactionName(), Integer.valueOf(1));
        }
    }

    /**
     * Füllt das Template und schreibt es in die Datei
     * 
     * @param context {@link VelocityContext}
     * @param template {@link Template}
     * @param fileName {@link String}
     */
    private void writeToFile(final VelocityContext context, final Template template, final String fileName) {
        final StringWriter writer = new StringWriter();
        template.merge(context, writer);
        try {
            final File journalReport = new File(
                    "c:\\Users\\dgroebner\\Saved Games\\Frontier Developments\\Elite Dangerous\\reports\\" + fileName);
            FileUtils.write(journalReport, writer.toString(), Charsets.UTF_8, false);
        } catch (final IOException e) {
            throw Throwables.propagate(e);
        }
    }

    /**
     * Parst die übergebene Datei
     * 
     * @param file {@link File}
     */
    public static void parse(final File file) {
        final EDJournalParser parser = new EDJournalParser(file);
        try {
            parser.doAction();
        } catch (final IOException e) {
            throw Throwables.propagate(e);
        }
    }

}
