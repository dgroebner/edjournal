package de.dgroebner.edjson.db.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.mapper.VRingMapper;
import de.dgroebner.edjson.db.model.VRings;

/**
 * DAO-Interface für den View 'vrings'
 * 
 * @author dgroebner
 */
@RegisterMapper(VRingMapper.class)
public interface VRingDao extends AbstractDao {

    /**
     * Gibt eine Liste der Ringe zurück
     * 
     * @return {@link List} von {@link VRings}
     */
    @SqlQuery("SELECT art, starsystemname, ringname, ringtype, starsystem_url, distanceInsystem, distanceToluku FROM vrings ORDER BY distanceToluku, ringname")
    List<VRings> list();

    /**
     * Zählt die vorhandenen Ringe
     * 
     * @return int
     */
    @SqlQuery("SELECT COUNT(*) FROM vrings")
    int count();

}
