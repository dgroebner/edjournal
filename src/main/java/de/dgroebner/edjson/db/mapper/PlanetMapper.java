package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.DBPlanet;

/**
 * Mapperklasse für die Datenbanktabelle 'planet'
 * 
 * @author dgroebner
 */
public class PlanetMapper extends AbstractMapper<DBPlanet> {

    public static final String COLUMN_STARSYSTEM_ID = "starsystem_id";

    public static final String COLUMN_NAME = "name";

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

    @Override
    public DBPlanet map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        /* @formatter:off */
        return DBPlanet.builder()
                .id(r.getInt(COLUMN_ID))
                .journalId(r.getInt(COLUMN_JOURNAL_ID))
                .starsystemId(r.getInt(COLUMN_STARSYSTEM_ID))
                .name(r.getString(COLUMN_NAME))
                .type(r.getString(COLUMN_TYPE))
                .distanceFromArrivalLS(r.getBigDecimal(COLUMN_DISTANCE_FROM_ARRIVAL_LS))
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
