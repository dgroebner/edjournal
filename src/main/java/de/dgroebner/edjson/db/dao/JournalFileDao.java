package de.dgroebner.edjson.db.dao;

import static de.dgroebner.edjson.db.mapper.JournalFileMapper.COLUMN_FILENAME;
import static de.dgroebner.edjson.db.mapper.JournalFileMapper.COLUMN_READDATE;

import java.util.Date;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

/**
 * DAO-Interface für die Tabelle JournalFile
 * 
 * @author dgroebner
 */
public interface JournalFileDao extends AbstractDao {

    /**
     * Zählt die Einträge für den übergebenen Dateinamen
     * 
     * @param filename {@link String}
     * @return int
     */
    @SqlQuery("SELECT COUNT(*) FROM journalfile WHERE filename = :filename")
    int countByName(@Bind(COLUMN_FILENAME) String filename);

    /**
     * Fügt eine neue Datei hinzu
     * 
     * @param filename {@link String}
     * @param readDate {@link Date}
     */
    @SqlUpdate("INSERT INTO journalfile (filename, readdate) VALUES (:filename, :readDate)")
    void insert(@Bind(COLUMN_FILENAME) String filename, @Bind(COLUMN_READDATE) Date readDate);

}
