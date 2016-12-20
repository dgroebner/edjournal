package de.dgroebner.edjson;

import org.apache.camel.main.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Elite Dangerous Journalparser basierend auf Apache Camel zum on-the-fly-Lesen der Journallogs
 * 
 * @author dgroebner
 */
public class EDJournalParserContinous {

    private static final Logger LOGGER = LoggerFactory.getLogger(EDJournalParser.class);

    private Main main;

    /**
     * Main - Methode
     * 
     * @param args {@link String}-Array
     * @throws Exception Exception
     */
    public static void main(final String[] args) throws Exception {
        final EDJournalParserContinous parser = new EDJournalParserContinous();
        parser.boot();
    }

    /**
     * Konfiguriert und startet den Camel Context
     * 
     * @throws Exception Exception
     */
    public void boot() throws Exception {
        main = new Main();
        main.addRouteBuilder(new EDJournalRouteBuilder());
        LOGGER.info("Starting Camel. Use ctrl + c to terminate the JVM.");
        main.run();
    }

}
