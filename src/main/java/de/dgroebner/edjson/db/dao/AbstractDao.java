package de.dgroebner.edjson.db.dao;

/**
 * Abstractes DAO-Interface für das Tabellenschema
 * 
 * @author dgroebner
 */
public interface AbstractDao {

    public static final String COLUMN_ID = "id";

    /**
     * Beendet die Verbindung
     */
    void close();

}
