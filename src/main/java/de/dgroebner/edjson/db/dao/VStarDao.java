package de.dgroebner.edjson.db.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.mapper.VStarMapper;
import de.dgroebner.edjson.db.model.VStar;

/**
 * DAO-Interface für den View 'vstar'
 * 
 * @author dgroebner
 */
@RegisterMapper(VStarMapper.class)
public interface VStarDao extends AbstractDao {

    /**
     * Gibt eine Liste der Sterne zurück
     * 
     * @return {@link List} von {@link VStar}
     */
    @SqlQuery("SELECT starname, type, stellar_mass, radius, absolute_magnitude, ageMY, surface_temperature, semi_major_axis, eccentricity, orbital_inclination, periapsis, orbital_period, rotation_period, starsystem_url, distanceInsystem, distanceToluku FROM vstar ORDER BY distanceToluku, starname")
    List<VStar> list();

}
