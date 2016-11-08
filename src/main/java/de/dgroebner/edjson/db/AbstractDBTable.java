package de.dgroebner.edjson.db;

import org.skife.jdbi.v2.DBI;

/**
 * Basisklasse für Datenbanktabellen
 * 
 * @author dgroebner
 */
public class AbstractDBTable {

    private final DBI dbi;

    /**
     * Constructor.
     *
     * @param dbi {@link DBI} Datenbankverbindung
     */
    public AbstractDBTable(final DBI dbi) {
        this.dbi = dbi;
    }

    /**
     * Gibt das gestezte Datenbankverbindungsobjekt zurück
     * 
     * @return {@link DBI}
     */
    protected final DBI getDbi() {
        return dbi;
    }

}
