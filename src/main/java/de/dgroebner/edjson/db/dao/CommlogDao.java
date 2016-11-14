package de.dgroebner.edjson.db.dao;

import static de.dgroebner.edjson.db.mapper.AbstractMapper.COLUMN_JOURNAL_ID;
import static de.dgroebner.edjson.db.mapper.CommlogMapper.COLUMN_CHANNEL;
import static de.dgroebner.edjson.db.mapper.CommlogMapper.COLUMN_MESSAGE;
import static de.dgroebner.edjson.db.mapper.CommlogMapper.COLUMN_RECEIVER;
import static de.dgroebner.edjson.db.mapper.CommlogMapper.COLUMN_SENDER;
import static de.dgroebner.edjson.db.mapper.CommlogMapper.COLUMN_TIMESTAMP;

import java.time.LocalDateTime;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.binder.LocalDateTimeBinder;
import de.dgroebner.edjson.db.mapper.CommlogMapper;

/**
 * DAO-Interface für die Tabelle 'commlog'
 * 
 * @author dgroebner
 */
@RegisterMapper(CommlogMapper.class)
public interface CommlogDao extends AbstractDao {

    /**
     * Fügt einen neuen Kommunikationseintrag hinzu und gibt die erzeugte ID zurück
     * 
     * @param journalId int
     * @param timestamp {@link LocalDateTime}
     * @param sender {@link String}
     * @param receiver {@link String}
     * @param channel {@link String}
     * @param message {@link String}
     * @return int
     */
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO commlog (journal_id, timestamp, sender, receiver, channel, message) VALUES (:journal_id, :timestamp, :sender, :receiver, :channel, :message)")
    int insert(@Bind(COLUMN_JOURNAL_ID) int journalId,
            @Bind(value = COLUMN_TIMESTAMP, binder = LocalDateTimeBinder.class) LocalDateTime timestamp,
            @Bind(COLUMN_SENDER) String sender, @Bind(COLUMN_RECEIVER) String receiver,
            @Bind(COLUMN_CHANNEL) String channel, @Bind(COLUMN_MESSAGE) String message);
}
