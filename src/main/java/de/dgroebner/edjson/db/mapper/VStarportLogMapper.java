package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.VStarportLog;

/**
 * Mapperklasse für den View 'vstarportlog'
 * 
 * @author dgroebner
 */
public class VStarportLogMapper extends AbstractMapper<VStarportLog> {

    public static final String COLUMN_ALLGIANCE = "allegiance";

    public static final String COLUMN_ECONOMY = "economy";

    public static final String COLUMN_SYSTEMNAME = "systemname";

    public static final String COLUMN_SYSTEM_URL = "system_url";

    public static final String COLUMN_PORTNAME = "portname";

    public static final String COLUMN_PORT_URL = "port_url";

    public static final String COLUMN_TIMESTAMP = "timestamp";

    public static final String COLUMN_FACTIONNAME = "factionname";

    public static final String COLUMN_FACTION_URL = "faction_url";

    public static final String COLUMN_GOVERNMENT = "government";

    public static final String COLUMN_TYPE = "type";

    @Override
    public VStarportLog map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        return new VStarportLog(r.getTimestamp(COLUMN_TIMESTAMP).toLocalDateTime(), r.getString(COLUMN_PORTNAME),
                r.getString(COLUMN_PORT_URL), r.getString(COLUMN_SYSTEMNAME), r.getString(COLUMN_SYSTEM_URL),
                r.getString(COLUMN_FACTIONNAME), r.getString(COLUMN_FACTION_URL), r.getString(COLUMN_TYPE),
                r.getString(COLUMN_ALLGIANCE), r.getString(COLUMN_GOVERNMENT), r.getString(COLUMN_ECONOMY));
    }

}
