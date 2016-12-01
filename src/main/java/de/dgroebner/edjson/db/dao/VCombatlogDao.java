package de.dgroebner.edjson.db.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.mapper.VCombatlogMapper;
import de.dgroebner.edjson.db.model.VCombatlog;

/**
 * DAO-Interface für den View 'vcombatlog'
 * 
 * @author dgroebner
 */
@RegisterMapper(VCombatlogMapper.class)
public interface VCombatlogDao extends AbstractDao {

    /**
     * Listet das komplette Combatlog auf
     * 
     * @return {@link List} von {@link VCombatlog}
     */
    @SqlQuery("SELECT TOP 50 timestamp, action, enemy, faction_name, faction_url, shipname, ship_url FROM vcombatlog ORDER BY timestamp DESC")
    List<VCombatlog> list();

}
