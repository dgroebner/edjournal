package de.dgroebner.edjson.db.dao;

import static de.dgroebner.edjson.db.mapper.VPlanetMapper.COLUMN_STARSYSTEMID;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.mapper.VPlanetMapper;
import de.dgroebner.edjson.db.model.VPlanet;
import de.dgroebner.edjson.db.model.VStar;

/**
 * DAO-Interface für den View 'vstar'
 * 
 * @author dgroebner
 */
@RegisterMapper(VPlanetMapper.class)
public interface VPlanetDao extends AbstractDao {

    /**
     * Gibt eine Liste der Planeten zurück
     * 
     * @return {@link List} von {@link VStar}
     */
    @SqlQuery("SELECT planetname, type, tidal_lock, terraform_state, atmosphere, volcanism, mass_em, radius, surface_gravity, surface_temperature, surface_pressure, landable, semi_major_axis, eccentricity, orbital_inclination, periapsis, orbital_period, rotation_period, starsystem_url, distanceInsystem, distanceToluku FROM vplanet ORDER BY distanceToluku, planetname")
    List<VPlanet> list();

    /**
     * Gibt eine Liste der Planeten für das übergebene Sternensystem zurück
     * 
     * @param starsystemId id des Starsystem
     * @return {@link List} von {@link VPlanet}
     */
    @SqlQuery("SELECT planetname, type, tidal_lock, terraform_state, atmosphere, volcanism, mass_em, radius, surface_gravity, surface_temperature, surface_pressure, landable, semi_major_axis, eccentricity, orbital_inclination, periapsis, orbital_period, rotation_period, starsystem_url, distanceInsystem, distanceToluku FROM vplanet WHERE starsystem_id = :starsystemId ORDER BY planetname")
    List<VPlanet> list(@Bind(COLUMN_STARSYSTEMID) Integer starsystemId);

}
