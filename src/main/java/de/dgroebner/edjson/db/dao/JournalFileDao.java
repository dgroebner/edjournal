package de.dgroebner.edjson.db.dao;

import static de.dgroebner.edjson.db.mapper.JournalFileMapper.COLUMN_FILENAME;
import static de.dgroebner.edjson.db.mapper.JournalFileMapper.COLUMN_READDATE;

import java.time.LocalDateTime;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.binder.LocalDateTimeBinder;
import de.dgroebner.edjson.db.mapper.JournalFileMapper;
import de.dgroebner.edjson.db.model.DBJournalFile;

/**
 * DAO-Interface für die Tabelle 'journalfile'
 * 
 * @author dgroebner
 */
@RegisterMapper(JournalFileMapper.class)
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
     * Gibt das Journalfile für den übergebenen Dateinamen zurück
     * 
     * @param filename {@link String}
     * @return {@link DBJournalFile}
     */
    @SqlQuery("SELECT id, filename, readdate FROM journalfile WHERE filename = :filename")
    DBJournalFile findByFilename(@Bind(COLUMN_FILENAME) String filename);

    /**
     * Fügt eine neue Datei hinzu und gibt die erzeugte ID zurück
     * 
     * @param filename {@link String}
     * @param readDate {@link LocalDateTime}
     * @return int
     */
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO journalfile (filename, readdate) VALUES (:filename, :readDate)")
    int insert(@Bind(COLUMN_FILENAME) String filename,
            @Bind(value = COLUMN_READDATE, binder = LocalDateTimeBinder.class) LocalDateTime readDate);

}
