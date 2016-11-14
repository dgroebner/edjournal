package de.dgroebner.edjson.db.dao;

import static de.dgroebner.edjson.db.mapper.AbstractMapper.COLUMN_JOURNAL_ID;
import static de.dgroebner.edjson.db.mapper.StarsystemVisitsMapper.COLUMN_SHIP_ID;
import static de.dgroebner.edjson.db.mapper.StarsystemVisitsMapper.COLUMN_STARSYSTEMID;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.mapper.StarsystemVisitsMapper;

/**
 * DAO-Interface für die Tabelle 'starsystems_visits'
 * 
 * @author dgroebner
 */
@RegisterMapper(StarsystemVisitsMapper.class)
public interface StarsystemVisitsDao extends AbstractDao {

    /**
     * Fügt ein neuen Sternenhafen ein
     * 
     * @param journalId int
     * @param starportId int
     * @param shipId int
     * @return int
     */
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO starsystem_visits (journal_id, starsystem_id, ship_id) VALUES (:journal_id, :starsystem_id, :ship_id)")
    int insert(@Bind(COLUMN_JOURNAL_ID) int journalId, @Bind(COLUMN_STARSYSTEMID) int starportId,
            @Bind(COLUMN_SHIP_ID) int shipId);
}
