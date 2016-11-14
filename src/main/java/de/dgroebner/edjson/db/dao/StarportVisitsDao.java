package de.dgroebner.edjson.db.dao;

import static de.dgroebner.edjson.db.mapper.AbstractMapper.COLUMN_JOURNAL_ID;
import static de.dgroebner.edjson.db.mapper.StarportVisitsMapper.COLUMN_SHIP_ID;
import static de.dgroebner.edjson.db.mapper.StarportVisitsMapper.COLUMN_STARPORTID;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.mapper.StarportVisitsMapper;

/**
 * DAO-Interface für die Tabelle 'starport_visits'
 * 
 * @author dgroebner
 */
@RegisterMapper(StarportVisitsMapper.class)
public interface StarportVisitsDao extends AbstractDao {

    /**
     * Fügt ein neuen Sternenhafen ein
     * 
     * @param journalId int
     * @param starportId int
     * @param shipId int
     * @return int
     */
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO starport_visits (journal_id, starport_id, ship_id) VALUES (:journal_id, :starport_id, :ship_id)")
    int insert(@Bind(COLUMN_JOURNAL_ID) int journalId, @Bind(COLUMN_STARPORTID) int starportId,
            @Bind(COLUMN_SHIP_ID) int shipId);
}
