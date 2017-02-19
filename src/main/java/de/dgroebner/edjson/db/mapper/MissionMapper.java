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
        /* @formatter:off */
        return DBMission.builder()
                .id(r.getInt(COLUMN_ID))
                .journalId(r.getInt(COLUMN_JOURNAL_ID))
                .missionId(r.getInt(COLUMN_MISSION_ID))
                .factionId(r.getInt(COLUMN_FACTION_ID))
                .name(r.getString(COLUMN_NAME))
                .commodity(r.getString(COLUMN_COMMODITY))
                .commodityCount(r.getInt(COLUMN_COMMODITY_COUNT))
                .passengerType(r.getString(COLUMN_PASSENGER_TYPE))
                .passengerCount(r.getInt(COLUMN_PASSENGER_COUNT))
                .passengerVip(r.getBoolean(COLUMN_PASSENGER_VIP))
                .passengerWanted(r.getBoolean(COLUMN_PASSENGER_WANTED))
                .destination(r.getString(COLUMN_DESTINATION))
                .destinationPort(r.getString(COLUMN_DESTINATION_PORT))
                .targetFaction(r.getString(COLUMN_TARGET_FACTION))
                .reward(r.getInt(COLUMN_REWARD))
                .expiry(r.getTimestamp(COLUMN_EXPIRY).toLocalDateTime())
                .status(r.getString(COLUMN_STATUS))
                .financeId(r.getInt(COLUMN_FINANCE_ID))
                .build();
        /* @formatter:on */
    }

}
