package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.DBStar;

/**
 * Mapperklasse für die Datenbanktabelle 'star'
 * 
 * @author dgroebner
 */
public class StarMapper extends AbstractMapper<DBStar> {

    public static final String COLUMN_STARSYSTEM_ID = "starsystem_id";

    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_TYPE = "type";

    public static final String COLUMN_DISTANCE_FROM_ARRIVAL_LS = "distance_from_arrival_ls";

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

    @Override
    public DBStar map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        /* @formatter:off */
        return DBStar.builder()
                .id(r.getInt(COLUMN_ID))
                .journalId(r.getInt(COLUMN_JOURNAL_ID))
                .starsystemId(r.getInt(COLUMN_STARSYSTEM_ID))
                .name(r.getString(COLUMN_NAME))
                .type(r.getString(COLUMN_TYPE))
                .distanceFromArrivalLS(r.getBigDecimal(COLUMN_DISTANCE_FROM_ARRIVAL_LS))
                .stellarMass(r.getBigDecimal(COLUMN_STELLAR_MASS))
                .radius(r.getBigDecimal(COLUMN_RADIUS))
                .absoluteMagnitude(r.getBigDecimal(COLUMN_ABSOLUTE_MAGNITUDE))
                .ageMY(r.getInt(COLUMN_AGE_MY))
                .surfaceTemperature(r.getBigDecimal(COLUMN_SURFACE_TEMPERATURE))
                .semiMajorAxis(r.getBigDecimal(COLUMN_SEMI_MAJOR_AXIS))
                .eccentricity(r.getBigDecimal(COLUMN_ECCENTRICITY))
                .orbitalInclination(r.getBigDecimal(COLUMN_ORBITAL_INCLINATION))
                .periapsis(r.getBigDecimal(COLUMN_PERIAPSIS))
                .orbitalPeriod(r.getBigDecimal(COLUMN_ORBITAL_PERIOD))
                .rotationPeriod(r.getBigDecimal(COLUMN_ROTATION_PERIOD))
                .build();
        /* @formatter:on */
    }

}
