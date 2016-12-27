package de.dgroebner.edjson.db.dao;

import static de.dgroebner.edjson.db.mapper.VStarportLogMapper.COLUMN_SYSTEMNAME;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
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

    /**
     * Selektiert die Liste der Raumhäfen im System mit dem übergebenen Namen
     * 
     * @param systemname {@link String} Systemname
     * @return {@link List} von {@link VStarportLog}
     */
    @SqlQuery("SELECT DISTINCT CONVERT(DATETIME, '01.01.2016', 104) AS timestamp, portname, port_url, systemname, system_url, factionname, faction_url, allegiance, type, government, economy, distanceToluku FROM vstarportvisits WHERE systemname = :systemname ORDER BY portname")
    List<VStarportLog> list(@Bind(COLUMN_SYSTEMNAME) String systemname);

}
