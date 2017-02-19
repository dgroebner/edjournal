package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.VPlanet;

/**
 * Mapperklasse für den View 'vplanet'
 * 
 * @author dgroebner
 */
public class VPlanetMapper extends AbstractMapper<VPlanet> {

    public static final String COLUMN_PLANETNAME = "planetname";

    public static final String COLUMN_TYPE = "type";

    public static final String COLUMN_DISTANCE_FROM_ARRIVAL_LS = "distance_from_arrival_ls";

    public static final String COLUMN_RADIUS = "radius";

    public static final String COLUMN_TIDAL_LOCK = "tidal_lock";

    public static final String COLUMN_TERRAFORM_STATE = "terraform_state";

    public static final String COLUMN_ATMOSPHERE = "atmosphere";

    public static final String COLUMN_VOLCANISM = "volcanism";

    public static final String COLUMN_MASS_EM = "mass_em";

    public static final String COLUMN_SURFACE_GRAVITY = "surface_gravity";

    public static final String COLUMN_SURFACE_TEMPERATURE = "surface_temperature";

    public static final String COLUMN_SURFACE_PRESSURE = "surface_pressure";

    public static final String COLUMN_LANDABLE = "landable";

    public static final String COLUMN_SEMI_MAJOR_AXIS = "semi_major_axis";

    public static final String COLUMN_ECCENTRICITY = "eccentricity";

    public static final String COLUMN_ORBITAL_INCLINATION = "orbital_inclination";

    public static final String COLUMN_PERIAPSIS = "periapsis";

    public static final String COLUMN_ORBITAL_PERIOD = "orbital_period";

    public static final String COLUMN_ROTATION_PERIOD = "rotation_period";

    public static final String COLUMN_STARSYSTEM_URL = "starsystem_url";

    public static final String COLUMN_DISTANCE_IN_SYSTEM = "distanceInsystem";

    public static final String COLUMN_DISTANCETOLUKU = "distanceToluku";

    public static final String COLUMN_STARSYSTEMID = "starsystemId";

    @Override
    public VPlanet map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        /* @formatter:off */
        return VPlanet.builder()
                .planetname(r.getString(COLUMN_PLANETNAME))
                .type(r.getString(COLUMN_TYPE))
                .tidalLock(r.getBoolean(COLUMN_TIDAL_LOCK))
                .terraformState(r.getString(COLUMN_TERRAFORM_STATE))
                .atmosphere(r.getString(COLUMN_ATMOSPHERE))
                .volcanism(r.getString(COLUMN_VOLCANISM))
                .massEM(r.getBigDecimal(COLUMN_MASS_EM))
                .radius(r.getBigDecimal(COLUMN_RADIUS))
                .surfaceGravity(r.getBigDecimal(COLUMN_SURFACE_GRAVITY))
                .surfaceTemperature(r.getBigDecimal(COLUMN_SURFACE_TEMPERATURE))
                .surfacePressure(r.getBigDecimal(COLUMN_SURFACE_PRESSURE))
                .landable(r.getBoolean(COLUMN_LANDABLE))
                .semiMajorAxis( r.getBigDecimal(COLUMN_SEMI_MAJOR_AXIS))
                .eccentricity(r.getBigDecimal(COLUMN_ECCENTRICITY))
                .orbitalInclination(r.getBigDecimal(COLUMN_ORBITAL_INCLINATION))
                .periapsis(r.getBigDecimal(COLUMN_PERIAPSIS))
                .orbitalPeriod(r.getBigDecimal(COLUMN_ORBITAL_PERIOD))
                .rotationPeriod(r.getBigDecimal(COLUMN_ROTATION_PERIOD))
                .starsystemUrl(r.getString(COLUMN_STARSYSTEM_URL))
                .distanceInSystem(r.getBigDecimal(COLUMN_DISTANCE_IN_SYSTEM))
                .distanceToluku(r.getBigDecimal(COLUMN_DISTANCETOLUKU))
                .build();
        /* @formatter:on */
    }

}
