package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.DBShip;

/**
 * Mapperklasse für die Datenbanktabelle 'ship'
 * 
 * @author dgroebner
 */
public class ShipMapper extends AbstractMapper<DBShip> {

    public static final String COLUMN_ED_ID = "edId";

    public static final String COLUMN_TYPE = "type";

    public static final String COLUMN_CALLSIGN = "callsign";

    @Override
    public DBShip map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        return new DBShip(r.getInt(COLUMN_ID), r.getInt(COLUMN_ED_ID), r.getString(COLUMN_TYPE),
                r.getString(COLUMN_CALLSIGN), r.getString(COLUMN_INARA_URL));
    }

}
