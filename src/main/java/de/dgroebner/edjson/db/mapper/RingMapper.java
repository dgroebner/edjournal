package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.DBRing;

/**
 * Mapperklasse für die Datenbanktabelle 'ring'
 * 
 * @author dgroebner
 */
public class RingMapper extends AbstractMapper<DBRing> {

    public static final String COLUMN_STARSYSTEM_ID = "starsystem_id";

    public static final String COLUMN_STAR_ID = "star_id";

    public static final String COLUMN_PLANET_ID = "planet_id";

    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_TYPE = "type";

    public static final String COLUMN_MASS_MT = "mass_mt";

    public static final String COLUMN_INNER_RAD = "inner_rad";

    public static final String COLUMN_OUTER_RAD = "outer_rad";

    @Override
    public DBRing map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        return new DBRing(r.getInt(COLUMN_ID), r.getInt(COLUMN_JOURNAL_ID), r.getInt(COLUMN_STARSYSTEM_ID),
                r.getInt(COLUMN_STAR_ID), r.getInt(COLUMN_PLANET_ID), r.getString(COLUMN_NAME),
                r.getString(COLUMN_TYPE), r.getString(COLUMN_MASS_MT), r.getString(COLUMN_INNER_RAD),
                r.getString(COLUMN_OUTER_RAD));
    }

}
