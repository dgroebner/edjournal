package de.dgroebner.edjson.db.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.mapper.VStarsystemLogMapper;
import de.dgroebner.edjson.db.model.VStarsystemLog;

/**
 * DAO-Interface für den View 'vstarsystemslog'
 * 
 * @author dgroebner
 */
@RegisterMapper(VStarsystemLogMapper.class)
public interface VStarsystemLogDao extends AbstractDao {

    /**
     * Selektiert die Liste der meistbesuchten Sternensysteme
     * 
     * @return {@link List} von {@link VStarsystemLog}
     */
    @SqlQuery("SELECT TOP 25 visits, systemname, system_url, factionname, faction_url, security, allegiance, government, economy, starpos FROM vstarsystemslog WHERE system_url IS NOT NULL ORDER BY visits DESC, systemname ASC")
    List<VStarsystemLog> list();

}
