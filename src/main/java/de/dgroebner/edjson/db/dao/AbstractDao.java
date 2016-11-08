package de.dgroebner.edjson.db.dao;

/**
 * Abstractes DAO-Interface für das Tabellenschema
 * 
 * @author dgroebner
 */
@FunctionalInterface
public interface AbstractDao {

    /**
     * Beendet die Verbindung
     */
    void close();

}
