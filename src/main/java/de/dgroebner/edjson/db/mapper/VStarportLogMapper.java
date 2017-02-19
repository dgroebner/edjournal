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

    public static final String COLUMN_DISTANCETOLUKU = "distanceToluku";

    @Override
    public VStarportLog map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        /* @formatter:off */
        return VStarportLog.builder()
                .timestamp(r.getTimestamp(COLUMN_TIMESTAMP).toLocalDateTime())
                .portname(r.getString(COLUMN_PORTNAME))
                .portUrl(r.getString(COLUMN_PORT_URL))
                .systemname(r.getString(COLUMN_SYSTEMNAME))
                .systemUrl(r.getString(COLUMN_SYSTEM_URL))
                .factionName(r.getString(COLUMN_FACTIONNAME))
                .factionUrl(r.getString(COLUMN_FACTION_URL))
                .type(r.getString(COLUMN_TYPE))
                .allegiance(r.getString(COLUMN_ALLGIANCE))
                .government(r.getString(COLUMN_GOVERNMENT))
                .economy(r.getString(COLUMN_ECONOMY))
                .distanceToluku(r.getBigDecimal(COLUMN_DISTANCETOLUKU))
                .build();
        /* @formatter:on */
    }

}
