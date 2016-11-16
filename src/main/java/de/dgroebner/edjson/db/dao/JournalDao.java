package de.dgroebner.edjson.db.dao;

import static de.dgroebner.edjson.db.mapper.JournalMapper.COLUMN_EVENT;
import static de.dgroebner.edjson.db.mapper.JournalMapper.COLUMN_JOURNALFILE_ID;
import static de.dgroebner.edjson.db.mapper.JournalMapper.COLUMN_MESSAGE;
import static de.dgroebner.edjson.db.mapper.JournalMapper.COLUMN_TIMESTAMP;

import java.time.LocalDateTime;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.binder.LocalDateTimeBinder;
import de.dgroebner.edjson.db.mapper.JournalMapper;
import de.dgroebner.edjson.db.model.DBJournal;

/**
 * DAO-Interface für die Tabelle 'journal'
 * 
 * @author dgroebner
 */
@RegisterMapper(JournalMapper.class)
public interface JournalDao extends AbstractDao {

    /**
     * Fügt einen neuen Journaleintrag hinzu und gibt die erzeugte ID zurück
     * 
     * @param journalfileId {@link String}
     * @param timestamp {@link LocalDateTime}
     * @param event {@link String}
     * @param message {@link String}
     * @return int
     */
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO journal (journalfile_id, timestamp, event, message) VALUES (:journalfileId, :timestamp, :event, :message)")
    int insert(@Bind(COLUMN_JOURNALFILE_ID) int journalfileId,
            @Bind(value = COLUMN_TIMESTAMP, binder = LocalDateTimeBinder.class) LocalDateTime timestamp,
            @Bind(COLUMN_EVENT) String event, @Bind(COLUMN_MESSAGE) String message);

    /**
     * Gibt eine Liste aller Journaleinträge zurück
     * 
     * @return {@link List} von {@link DBJournal}
     */
    @SqlQuery("SELECT TOP 1000 id, journalfile_id, timestamp, event, message FROM journal ORDER BY timestamp DESC")
    List<DBJournal> list();
}
