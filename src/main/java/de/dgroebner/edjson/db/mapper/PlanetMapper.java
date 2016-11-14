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
        return new DBPlanet(r.getInt(COLUMN_ID), r.getInt(COLUMN_JOURNAL_ID), r.getInt(COLUMN_STARSYSTEM_ID),
                r.getString(COLUMN_NAME), r.getString(COLUMN_TYPE), r.getBigDecimal(COLUMN_DISTANCE_FROM_ARRIVAL_LS),
                r.getBoolean(COLUMN_TIDAL_LOCK), r.getString(COLUMN_TERRAFORM_STATE), r.getString(COLUMN_ATMOSPHERE),
                r.getString(COLUMN_VOLCANISM), r.getBigDecimal(COLUMN_MASS_EM), r.getBigDecimal(COLUMN_RADIUS),
                r.getBigDecimal(COLUMN_SURFACE_GRAVITY), r.getBigDecimal(COLUMN_SURFACE_TEMPERATURE),
                r.getBigDecimal(COLUMN_SURFACE_PRESSURE), r.getBoolean(COLUMN_LANDABLE),
                r.getBigDecimal(COLUMN_SEMI_MAJOR_AXIS), r.getBigDecimal(COLUMN_ECCENTRICITY),
                r.getBigDecimal(COLUMN_ORBITAL_INCLINATION), r.getBigDecimal(COLUMN_PERIAPSIS),
                r.getBigDecimal(COLUMN_ORBITAL_PERIOD), r.getBigDecimal(COLUMN_ROTATION_PERIOD));
    }

}
