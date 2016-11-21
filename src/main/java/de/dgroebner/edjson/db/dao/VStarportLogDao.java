package de.dgroebner.edjson.db.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.mapper.VStarportLogMapper;
import de.dgroebner.edjson.db.model.VStarportLog;

/**
 * DAO-Interface für den View 'vstarportlog'
 * 
 * @author dgroebner
 */
@RegisterMapper(VStarportLogMapper.class)
public interface VStarportLogDao extends AbstractDao {

    /**
     * Selektiert die Liste der letztbesuchten Raumhäfen
     * 
     * @return {@link List} von {@link VStarportLog}
     */
    @SqlQuery("SELECT TOP 25 timestamp, portname, port_url, systemname, system_url, factionname, faction_url, allegiance, type, government, economy, distanceToluku FROM vstarportvisits ORDER BY timestamp DESC")
    List<VStarportLog> list();

}
