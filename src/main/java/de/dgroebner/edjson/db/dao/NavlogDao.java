package de.dgroebner.edjson.db.dao;

import static de.dgroebner.edjson.db.mapper.AbstractMapper.COLUMN_JOURNAL_ID;
import static de.dgroebner.edjson.db.mapper.NavlogMapper.COLUMN_DISTANCE;
import static de.dgroebner.edjson.db.mapper.NavlogMapper.COLUMN_FUEL_USED;
import static de.dgroebner.edjson.db.mapper.NavlogMapper.COLUMN_SHIP_ID;
import static de.dgroebner.edjson.db.mapper.NavlogMapper.COLUMN_TIMESTAMP;
import static de.dgroebner.edjson.db.mapper.NavlogMapper.COLUMN_TOSYSTEM_ID;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.binder.LocalDateTimeBinder;
import de.dgroebner.edjson.db.mapper.NavlogMapper;

/**
 * DAO-Interface für die Tabelle 'navlog'
 * 
 * @author dgroebner
 */
@RegisterMapper(NavlogMapper.class)
public interface NavlogDao extends AbstractDao {

    /**
     * Fügt einen neuen Navigationseintrag hinzu und gibt die erzeugte ID zurück
     * 
     * @param journalId int
     * @param shipId int
     * @param timestamp {@link LocalDateTime}
     * @param tosystemId int
     * @param distance {@link BigDecimal}
     * @param fuelUsed {@link BigDecimal}
     * @return int
     */
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO navlog (journal_id, ship_id, timestamp, tosystem_id, distance, fuelused) VALUES (:journal_id, :ship_id, :timestamp, :tosystem_id, :distance, :fuelused)")
    int insert(@Bind(COLUMN_JOURNAL_ID) int journalId, @Bind(COLUMN_SHIP_ID) int shipId,
            @Bind(value = COLUMN_TIMESTAMP, binder = LocalDateTimeBinder.class) LocalDateTime timestamp,
            @Bind(COLUMN_TOSYSTEM_ID) int tosystemId, @Bind(COLUMN_DISTANCE) BigDecimal distance,
            @Bind(COLUMN_FUEL_USED) BigDecimal fuelUsed);


}
