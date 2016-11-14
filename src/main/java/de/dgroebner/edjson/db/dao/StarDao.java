package de.dgroebner.edjson.db.dao;

import static de.dgroebner.edjson.db.mapper.AbstractMapper.COLUMN_JOURNAL_ID;
import static de.dgroebner.edjson.db.mapper.StarMapper.COLUMN_ABSOLUTE_MAGNITUDE;
import static de.dgroebner.edjson.db.mapper.StarMapper.COLUMN_AGE_MY;
import static de.dgroebner.edjson.db.mapper.StarMapper.COLUMN_DISTANCE_FROM_ARRIVAL_LS;
import static de.dgroebner.edjson.db.mapper.StarMapper.COLUMN_ECCENTRICITY;
import static de.dgroebner.edjson.db.mapper.StarMapper.COLUMN_NAME;
import static de.dgroebner.edjson.db.mapper.StarMapper.COLUMN_ORBITAL_INCLINATION;
import static de.dgroebner.edjson.db.mapper.StarMapper.COLUMN_ORBITAL_PERIOD;
import static de.dgroebner.edjson.db.mapper.StarMapper.COLUMN_PERIAPSIS;
import static de.dgroebner.edjson.db.mapper.StarMapper.COLUMN_RADIUS;
import static de.dgroebner.edjson.db.mapper.StarMapper.COLUMN_ROTATION_PERIOD;
import static de.dgroebner.edjson.db.mapper.StarMapper.COLUMN_SEMI_MAJOR_AXIS;
import static de.dgroebner.edjson.db.mapper.StarMapper.COLUMN_STARSYSTEM_ID;
import static de.dgroebner.edjson.db.mapper.StarMapper.COLUMN_STELLAR_MASS;
import static de.dgroebner.edjson.db.mapper.StarMapper.COLUMN_SURFACE_TEMPERATURE;
import static de.dgroebner.edjson.db.mapper.StarMapper.COLUMN_TYPE;

import java.math.BigDecimal;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.mapper.StarMapper;

/**
 * DAO-Interface für die Tabelle 'star'
 * 
 * @author dgroebner
 */
@RegisterMapper(StarMapper.class)
public interface StarDao extends AbstractDao {

    /**
     * Fügt ein neuen Stern ein
     * 
     * @param journalId int
     * @param starsystemId int
     * @param name {@link String}
     * @param type {@link String}
     * @param distanceFromArrivalLS {@link BigDecimal}
     * @param stellarMass {@link BigDecimal}
     * @param radius {@link BigDecimal}
     * @param absoluteMagnitude {@link BigDecimal}
     * @param ageMY int
     * @param surfaceTemperature {@link BigDecimal}
     * @param semiMajorAxis {@link BigDecimal}
     * @param eccentricity {@link BigDecimal}
     * @param orbitalInclination {@link BigDecimal}
     * @param periapsis {@link BigDecimal}
     * @param orbitalPeriod {@link BigDecimal}
     * @param rotationPeriod {@link BigDecimal}
     * @return int
     */
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO star (journal_id, starsystem_id, name, type, distance_from_arrival_ls, stellar_mass, radius, absolute_magnitude, ageMY, surface_temperature, semi_major_axis, eccentricity, orbital_inclination, periapsis, orbital_period, rotation_period) VALUES (:journal_id, :starsystem_id, :name, :type, :distance_from_arrival_ls, :stellar_mass, :radius, :absolute_magnitude, :ageMY, :surface_temperature, :semi_major_axis, :eccentricity, :orbital_inclination, :periapsis, :orbital_period, :rotation_period)")
    @SuppressWarnings("squid:S00107")
    int insert(@Bind(COLUMN_JOURNAL_ID) int journalId, @Bind(COLUMN_STARSYSTEM_ID) int starsystemId,
            @Bind(COLUMN_NAME) String name, @Bind(COLUMN_TYPE) String type,
            @Bind(COLUMN_DISTANCE_FROM_ARRIVAL_LS) BigDecimal distanceFromArrivalLS,
            @Bind(COLUMN_STELLAR_MASS) BigDecimal stellarMass, @Bind(COLUMN_RADIUS) BigDecimal radius,
            @Bind(COLUMN_ABSOLUTE_MAGNITUDE) BigDecimal absoluteMagnitude, @Bind(COLUMN_AGE_MY) int ageMY,
            @Bind(COLUMN_SURFACE_TEMPERATURE) BigDecimal surfaceTemperature,
            @Bind(COLUMN_SEMI_MAJOR_AXIS) BigDecimal semiMajorAxis, @Bind(COLUMN_ECCENTRICITY) BigDecimal eccentricity,
            @Bind(COLUMN_ORBITAL_INCLINATION) BigDecimal orbitalInclination,
            @Bind(COLUMN_PERIAPSIS) BigDecimal periapsis, @Bind(COLUMN_ORBITAL_PERIOD) BigDecimal orbitalPeriod,
            @Bind(COLUMN_ROTATION_PERIOD) BigDecimal rotationPeriod);

}
