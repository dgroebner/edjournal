package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.VStar;

/**
 * Mapperklasse für den View 'vstar'
 * 
 * @author dgroebner
 */
public class VStarMapper extends AbstractMapper<VStar> {

    public static final String COLUMN_STARSYSTEM_URL = "starsystem_url";

    public static final String COLUMN_STARNAME = "starname";

    public static final String COLUMN_TYPE = "type";

    public static final String COLUMN_STELLAR_MASS = "stellar_mass";

    public static final String COLUMN_RADIUS = "radius";

    public static final String COLUMN_ABSOLUTE_MAGNITUDE = "absolute_magnitude";

    public static final String COLUMN_AGE_MY = "ageMY";

    public static final String COLUMN_SURFACE_TEMPERATURE = "surface_temperature";

    public static final String COLUMN_SEMI_MAJOR_AXIS = "semi_major_axis";

    public static final String COLUMN_ECCENTRICITY = "eccentricity";

    public static final String COLUMN_ORBITAL_INCLINATION = "orbital_inclination";

    public static final String COLUMN_PERIAPSIS = "periapsis";

    public static final String COLUMN_ORBITAL_PERIOD = "orbital_period";

    public static final String COLUMN_ROTATION_PERIOD = "rotation_period";

    public static final String COLUMN_DISTANCE_IN_SYSTEM = "distanceInsystem";

    public static final String COLUMN_DISTANCETOLUKU = "distanceToluku";

    public static final String COLUMN_STARSYSTEMID = "starsystemId";

    @Override
    public VStar map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        return new VStar(r.getString(COLUMN_STARNAME), r.getString(COLUMN_TYPE), r.getBigDecimal(COLUMN_STELLAR_MASS),
                r.getBigDecimal(COLUMN_RADIUS), r.getBigDecimal(COLUMN_ABSOLUTE_MAGNITUDE), r.getInt(COLUMN_AGE_MY),
                r.getBigDecimal(COLUMN_SURFACE_TEMPERATURE), r.getBigDecimal(COLUMN_SEMI_MAJOR_AXIS),
                r.getBigDecimal(COLUMN_ECCENTRICITY), r.getBigDecimal(COLUMN_ORBITAL_INCLINATION),
                r.getBigDecimal(COLUMN_PERIAPSIS), r.getBigDecimal(COLUMN_ORBITAL_PERIOD),
                r.getBigDecimal(COLUMN_ROTATION_PERIOD), r.getString(COLUMN_STARSYSTEM_URL),
                r.getBigDecimal(COLUMN_DISTANCE_IN_SYSTEM), r.getBigDecimal(COLUMN_DISTANCETOLUKU));
    }

}
