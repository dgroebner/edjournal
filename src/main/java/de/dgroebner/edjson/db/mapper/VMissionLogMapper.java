package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.VMissionLog;

/**
 * Mapperklasse für des View 'vmissionlog'
 * 
 * @author dgroebner
 */
public class VMissionLogMapper extends AbstractMapper<VMissionLog> {

    public static final String COLUMN_MISSIONNAME = "missionname";

    public static final String COLUMN_FACTIONNAME = "factionname";

    public static final String COLUMN_COMMODITY = "commodity";

    public static final String COLUMN_COMMODITY_COUNT = "commodity_count";

    public static final String COLUMN_PASSENGER_TYPE = "passenger_type";

    public static final String COLUMN_PASSENGER_COUNT = "passenger_count";

    public static final String COLUMN_PASSENGER_VIP = "passenger_vip";

    public static final String COLUMN_PASSENGER_WANTED = "passenger_wanted";

    public static final String COLUMN_DESTINATION = "destination";

    public static final String COLUMN_DESTINATION_PORT = "destination_port";

    public static final String COLUMN_STATUS = "status";

    public static final String COLUMN_REWARD = "reward";

    @Override
    public VMissionLog map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        return new VMissionLog(r.getString(COLUMN_FACTIONNAME), r.getString(COLUMN_MISSIONNAME),
                r.getString(COLUMN_COMMODITY), r.getInt(COLUMN_COMMODITY_COUNT), r.getString(COLUMN_PASSENGER_TYPE),
                r.getInt(COLUMN_PASSENGER_COUNT), r.getBoolean(COLUMN_PASSENGER_VIP),
                r.getBoolean(COLUMN_PASSENGER_WANTED), r.getString(COLUMN_DESTINATION),
                r.getString(COLUMN_DESTINATION_PORT), r.getString(COLUMN_STATUS), r.getInt(COLUMN_REWARD));
    }

}
