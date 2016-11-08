package de.dgroebner.edjson.db.dao;

import static de.dgroebner.edjson.db.mapper.JournalMapper.COLUMN_EVENT;
import static de.dgroebner.edjson.db.mapper.JournalMapper.COLUMN_JOURNALFILE_ID;
import static de.dgroebner.edjson.db.mapper.JournalMapper.COLUMN_MESSAGE;
import static de.dgroebner.edjson.db.mapper.JournalMapper.COLUMN_TIMESTAMP;

import java.time.LocalDateTime;
import java.util.Date;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.binder.LocalDateTimeBinder;
import de.dgroebner.edjson.db.mapper.JournalMapper;

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
     * @param timestamp {@link Date}
     * @param event {@link String}
     * @param message {@link String}
     * @return int
     */
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO journal (journalfile_id, timestamp, event, message) VALUES (:journalfileId, :timestamp, :event, :message)")
    int insert(@Bind(COLUMN_JOURNALFILE_ID) int journalfileId,
            @Bind(value = COLUMN_TIMESTAMP, binder = LocalDateTimeBinder.class) LocalDateTime timestamp,
            @Bind(COLUMN_EVENT) String event, @Bind(COLUMN_MESSAGE) String message);

}
