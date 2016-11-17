package de.dgroebner.edjson;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.tools.generic.NumberTool;
import org.json.JSONObject;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;

import de.dgroebner.edjson.db.Financedata;
import de.dgroebner.edjson.db.Journal;
import de.dgroebner.edjson.db.JournalFile;
import de.dgroebner.edjson.db.Mission;
import de.dgroebner.edjson.db.Navlog;
import de.dgroebner.edjson.db.Ship;
import de.dgroebner.edjson.db.Starport;
import de.dgroebner.edjson.db.Starsystem;
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

    private File file;

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
        context.put("datetool", new LocalDateTimeTool());
        context.put("numbertool", new NumberTool());
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

        final Template journalTemplate = Velocity.getTemplate("templates/journalTemplate.vm");

        final StringWriter sw = new StringWriter();
        journalTemplate.merge(context, sw);
        try {
            final File journalReport = new File(
                    "c:\\Users\\dgroebner\\Saved Games\\Frontier Developments\\Elite Dangerous\\reports\\journal.html");
            FileUtils.write(journalReport, sw.toString(), Charsets.UTF_8, false);
        } catch (IOException e) {
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
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

}
