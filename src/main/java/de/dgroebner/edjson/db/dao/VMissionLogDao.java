package de.dgroebner.edjson.db.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.mapper.VMissionLogMapper;
import de.dgroebner.edjson.db.model.VMissionLog;

/**
 * DAO-Interface für den View 'VMissionLog'
 * 
 * @author dgroebner
 */
@RegisterMapper(VMissionLogMapper.class)
public interface VMissionLogDao extends AbstractDao {

    /**
     * Liefert eine Liste der letzten Missionen
     * 
     * @return {@link List} von {@link VMissionLog}
     */
    @SqlQuery("SELECT TOP 25 factionname, missionname, commodity, commodity_count, passenger_type, passenger_count, passenger_vip, passenger_wanted, destination, destination_port, status, reward, missionid FROM vmissionlog ORDER BY missionid DESC")
    List<VMissionLog> list();
}
