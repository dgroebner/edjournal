package de.dgroebner.edjson.db.dao;

import static de.dgroebner.edjson.db.mapper.AbstractMapper.COLUMN_JOURNAL_ID;
import static de.dgroebner.edjson.db.mapper.CombatlogMapper.COLUMN_ACTION;
import static de.dgroebner.edjson.db.mapper.CombatlogMapper.COLUMN_ENEMY;
import static de.dgroebner.edjson.db.mapper.CombatlogMapper.COLUMN_FACTION_ID;
import static de.dgroebner.edjson.db.mapper.CombatlogMapper.COLUMN_REWARD;
import static de.dgroebner.edjson.db.mapper.CombatlogMapper.COLUMN_SHIP_ID;
import static de.dgroebner.edjson.db.mapper.CombatlogMapper.COLUMN_TIMESTAMP;

import java.time.LocalDateTime;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.binder.LocalDateTimeBinder;
import de.dgroebner.edjson.db.mapper.CombatlogMapper;
import de.dgroebner.edjson.db.model.DBCombatlog;

/**
 * DAO-Interface für die Tabelle 'combatlog'
 * 
 * @author dgroebner
 */
@RegisterMapper(CombatlogMapper.class)
public interface CombatlogDao extends AbstractDao {

    /**
     * Fügt einen neuen Kampflogeintrag hinzu und gibt die erzeugte ID zurück
     * 
     * @param journalId int
     * @param shipId int
     * @param timestamp {@link LocalDateTime}
     * @param action {@link String}
     * @param enemy {@link String}
     * @param factionId int
     * @param reward int
     * @return int
     */
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO combatlog (journal_id, ship_id, timestamp, action, enemy, faction_id, reward) VALUES (:journal_id, :ship_id, :timestamp, :action, :enemy, :faction_id, :reward)")
    int insert(@Bind(COLUMN_JOURNAL_ID) int journalId, @Bind(COLUMN_SHIP_ID) int shipId,
            @Bind(value = COLUMN_TIMESTAMP, binder = LocalDateTimeBinder.class) LocalDateTime timestamp,
            @Bind(COLUMN_ACTION) String action, @Bind(COLUMN_ENEMY) String enemy,
            @Bind(COLUMN_FACTION_ID) int factionId, @Bind(COLUMN_REWARD) int reward);

    /**
     * Listet das komplette Combatlog auf
     * 
     * @return {@link List} von {@link DBCombatlog}
     */
    @SqlQuery("SELECT journal_id, ship_id, timestamp, action, enemy, faction_id, reward FROM combatlog")
    List<DBCombatlog> list();


}
