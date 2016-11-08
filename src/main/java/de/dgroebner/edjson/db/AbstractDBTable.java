package de.dgroebner.edjson.db;

import org.skife.jdbi.v2.DBI;

/**
 * Basisklasse für Datenbanktabellen
 * 
 * @author dgroebner
 */
public class AbstractDBTable {

    private final DBI dbi;

    private int id;

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

    protected final void setId(final int id) {
        this.id = id;
    }

    /**
     * Gibt die id des geschriebenen Datensatzes zurück
     * 
     * @return int
     */
    public final int getDatabaseId() {
        return id;
    }

}
