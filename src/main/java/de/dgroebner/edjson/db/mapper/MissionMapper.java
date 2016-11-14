package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.DBMission;

/**
 * Mapperklasse für die Datenbanktabelle 'mission'
 * 
 * @author dgroebner
 */
public class MissionMapper extends AbstractMapper<DBMission> {

    public static final String COLUMN_MISSION_ID = "missionId";

    public static final String COLUMN_FACTION_ID = "faction_id";

    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_COMMODITY = "commodity";

    public static final String COLUMN_COMMODITY_COUNT = "commodity_count";

    public static final String COLUMN_PASSENGER_TYPE = "passenger_type";

    public static final String COLUMN_PASSENGER_COUNT = "passenger_count";

    public static final String COLUMN_PASSENGER_VIP = "passenger_vip";

    public static final String COLUMN_PASSENGER_WANTED = "passenger_wanted";

    public static final String COLUMN_DESTINATION = "destination";

    public static final String COLUMN_DESTINATION_PORT = "destination_port";

    public static final String COLUMN_TARGET_FACTION = "target_faction";

    public static final String COLUMN_REWARD = "reward";

    public static final String COLUMN_EXPIRY = "expiry";

    public static final String COLUMN_STATUS = "status";

    public static final String COLUMN_FINANCE_ID = "finance_id";

    @Override
    public DBMission map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        return new DBMission(r.getInt(COLUMN_ID), r.getInt(COLUMN_JOURNAL_ID), r.getInt(COLUMN_MISSION_ID),
                r.getInt(COLUMN_FACTION_ID), r.getString(COLUMN_NAME), r.getString(COLUMN_COMMODITY),
                r.getInt(COLUMN_COMMODITY_COUNT), r.getString(COLUMN_PASSENGER_TYPE), r.getInt(COLUMN_PASSENGER_COUNT),
                r.getBoolean(COLUMN_PASSENGER_VIP), r.getBoolean(COLUMN_PASSENGER_WANTED),
                r.getString(COLUMN_DESTINATION), r.getString(COLUMN_DESTINATION_PORT),
                r.getString(COLUMN_TARGET_FACTION), r.getInt(COLUMN_REWARD), r.getTimestamp(COLUMN_EXPIRY)
                        .toLocalDateTime(), r.getString(COLUMN_STATUS), r.getInt(COLUMN_FINANCE_ID));
    }

}
