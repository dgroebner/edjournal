package de.dgroebner.edjson.db.dao;

import static de.dgroebner.edjson.db.mapper.AbstractMapper.COLUMN_JOURNAL_ID;
import static de.dgroebner.edjson.db.mapper.PlanetMapper.COLUMN_ATMOSPHERE;
import static de.dgroebner.edjson.db.mapper.PlanetMapper.COLUMN_DISTANCE_FROM_ARRIVAL_LS;
import static de.dgroebner.edjson.db.mapper.PlanetMapper.COLUMN_ECCENTRICITY;
import static de.dgroebner.edjson.db.mapper.PlanetMapper.COLUMN_LANDABLE;
import static de.dgroebner.edjson.db.mapper.PlanetMapper.COLUMN_MASS_EM;
import static de.dgroebner.edjson.db.mapper.PlanetMapper.COLUMN_NAME;
import static de.dgroebner.edjson.db.mapper.PlanetMapper.COLUMN_ORBITAL_INCLINATION;
import static de.dgroebner.edjson.db.mapper.PlanetMapper.COLUMN_ORBITAL_PERIOD;
import static de.dgroebner.edjson.db.mapper.PlanetMapper.COLUMN_PERIAPSIS;
import static de.dgroebner.edjson.db.mapper.PlanetMapper.COLUMN_RADIUS;
import static de.dgroebner.edjson.db.mapper.PlanetMapper.COLUMN_ROTATION_PERIOD;
import static de.dgroebner.edjson.db.mapper.PlanetMapper.COLUMN_SEMI_MAJOR_AXIS;
import static de.dgroebner.edjson.db.mapper.PlanetMapper.COLUMN_STARSYSTEM_ID;
import static de.dgroebner.edjson.db.mapper.PlanetMapper.COLUMN_SURFACE_GRAVITY;
import static de.dgroebner.edjson.db.mapper.PlanetMapper.COLUMN_SURFACE_PRESSURE;
import static de.dgroebner.edjson.db.mapper.PlanetMapper.COLUMN_SURFACE_TEMPERATURE;
import static de.dgroebner.edjson.db.mapper.PlanetMapper.COLUMN_TERRAFORM_STATE;
import static de.dgroebner.edjson.db.mapper.PlanetMapper.COLUMN_TIDAL_LOCK;
import static de.dgroebner.edjson.db.mapper.PlanetMapper.COLUMN_TYPE;
import static de.dgroebner.edjson.db.mapper.PlanetMapper.COLUMN_VOLCANISM;

import java.math.BigDecimal;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.mapper.StarMapper;

/**
 * DAO-Interface für die Tabelle 'star'
 * 
 * @author dgroebner
 */
@RegisterMapper(StarMapper.class)
public interface PlanetDao extends AbstractDao {

    /**
     * Fügt ein neuen Stern ein
     * 
     * @param journalId int
     * @param starsystemId int
     * @param name {@link String}
     * @param type {@link String}
     * @param distancFromArrivalLS {@link BigDecimal}
     * @param tidalLock boolean
     * @param terraformState {@link String}
     * @param atmosphere {@link String}
     * @param volcanism {@link String}
     * @param massEM {@link BigDecimal}
     * @param radius {@link BigDecimal}
     * @param surfaceGravity {@link BigDecimal}
     * @param surfaceTemperature {@link BigDecimal}
     * @param surfacePressure {@link BigDecimal}
     * @param landable boolean
     * @param semiMajorAxis {@link BigDecimal}
     * @param eccentricity {@link BigDecimal}
     * @param orbitalInclination {@link BigDecimal}
     * @param periapsis {@link BigDecimal}
     * @param orbitalPeriod {@link BigDecimal}
     * @param rotationPeriod {@link BigDecimal}
     * @return int
     */
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO planet (journal_id, starsystem_id, name, type, distance_from_arrival_ls, tidal_lock, terraform_state, atmosphere, volcanism, mass_em, radius, surface_gravity, surface_temperature, surface_pressure, landable, semi_major_axis, eccentricity, orbital_inclination, periapsis, orbital_period, rotation_period) VALUES (:journal_id, :starsystem_id, :name, :type, :distance_from_arrival_ls, :tidal_lock, :terraform_state, :atmosphere, :volcanism, :mass_em, :radius, :surface_gravity, :surface_temperature, :surface_pressure, :landable, :semi_major_axis, :eccentricity, :orbital_inclination, :periapsis, :orbital_period, :rotation_period)")
    @SuppressWarnings("squid:S00107")
    int insert(@Bind(COLUMN_JOURNAL_ID) int journalId, @Bind(COLUMN_STARSYSTEM_ID) int starsystemId,
            @Bind(COLUMN_NAME) String name, @Bind(COLUMN_TYPE) String type,
            @Bind(COLUMN_DISTANCE_FROM_ARRIVAL_LS) BigDecimal distancFromArrivalLS,
            @Bind(COLUMN_TIDAL_LOCK) boolean tidalLock, @Bind(COLUMN_TERRAFORM_STATE) String terraformState,
            @Bind(COLUMN_ATMOSPHERE) String atmosphere, @Bind(COLUMN_VOLCANISM) String volcanism,
            @Bind(COLUMN_MASS_EM) BigDecimal massEM, @Bind(COLUMN_RADIUS) BigDecimal radius,
            @Bind(COLUMN_SURFACE_GRAVITY) BigDecimal surfaceGravity,
            @Bind(COLUMN_SURFACE_TEMPERATURE) BigDecimal surfaceTemperature,
            @Bind(COLUMN_SURFACE_PRESSURE) BigDecimal surfacePressure, @Bind(COLUMN_LANDABLE) boolean landable,
            @Bind(COLUMN_SEMI_MAJOR_AXIS) BigDecimal semiMajorAxis, @Bind(COLUMN_ECCENTRICITY) BigDecimal eccentricity,
            @Bind(COLUMN_ORBITAL_INCLINATION) BigDecimal orbitalInclination,
            @Bind(COLUMN_PERIAPSIS) BigDecimal periapsis, @Bind(COLUMN_ORBITAL_PERIOD) BigDecimal orbitalPeriod,
            @Bind(COLUMN_ROTATION_PERIOD) BigDecimal rotationPeriod);

    /**
     * Gibt die id des Planeten für den übergebenen Namen zurück
     * 
     * @param name {@link String}
     * @return int
     */
    @SqlQuery("SELECT id FROM planet WHERE name = :name")
    int getIdForName(@Bind(COLUMN_NAME) String name);

}
