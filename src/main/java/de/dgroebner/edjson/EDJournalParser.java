package de.dgroebner.edjson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;

import de.dgroebner.edjson.model.EDJournalEvents;
import de.dgroebner.edjson.model.JournalModel;
import de.dgroebner.edjson.model.data.Bounty;
import de.dgroebner.edjson.model.data.Scan;

/**
 * Parser für das Elite Dangerous Journal
 * 
 * @author dgroebner
 */
public class EDJournalParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(EDJournalParser.class);

    private File file;

    /**
     * Constructor.
     *
     * @param file {@link File} zu parsendes Journalfile
     */
    public EDJournalParser(final File file) {
        this.file = file;
    }

    private void doAction() throws IOException {
        normalizeFileLines(FileUtils.readLines(file, "UTF-8")).forEach(this::parseLine);
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
     * Parst die Dateizeile
     * 
     * @param fileline {@link String}
     */
    private void parseLine(final String fileline) {
        final JSONObject obj = new JSONObject(fileline);

        final EDJournalEvents event = EDJournalEvents.forCode(obj.getString("event"));
        final JournalModel model = event.getModel(obj);

        LOGGER.info("{} : {} : {}", file.getAbsolutePath(), obj.getString("timestamp"), obj.get("event"));
        LOGGER.info(model.toString());

        if (model instanceof Scan) {
            final Scan scan = (Scan) model;
            LOGGER.info("Materialen vorhanden: {} Werte: {}",
                    Boolean.valueOf(scan.getMaterials().areMaterialsPresent()),
                    scan.getMaterials());
            LOGGER.info("Ringe: {}", scan.getRings());
        } else if (model instanceof Bounty) {
            final Bounty bounty = (Bounty) model;
            LOGGER.info("Rewards per Faction: {}", bounty.getRewardPerFaction());
        }

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
