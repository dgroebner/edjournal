package de.dgroebner.edjson.processor;

import org.apache.camel.Processor;
import org.skife.jdbi.v2.DBI;

public abstract class AbstractProcessor implements Processor {

    private final DBI dbi = new DBI("jdbc:jtds:sqlserver://localhost:1433", "edjournaltest", "edjournaltest");

    /**
     * Gibt das Datenbankzugriffsobjekt zurück
     * 
     * @return {@link DBI}
     */
    public DBI getDbi() {
        return dbi;
    }

}
