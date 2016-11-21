package de.dgroebner.edjson.db.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.mapper.VNavlogMapper;
import de.dgroebner.edjson.db.model.VNavlog;

/**
 * DAO-Interface für den View 'vnavlog'
 * 
 * @author dgroebner
 */
@RegisterMapper(VNavlogMapper.class)
public interface VNavlogDao extends AbstractDao {

    /**
     * Fügt einen neuen Navigationseintrag hinzu und gibt die erzeugte ID zurück
     * 
     * @return {@link VNavlog}
     */
    @SqlQuery("SELECT TOP 50 shipname, shiptype, ship_url, timestamp, systemname, system_url, distance, fuelused, distanceToluku FROM vnavlog ORDER BY timestamp DESC")
    List<VNavlog> list();

}
