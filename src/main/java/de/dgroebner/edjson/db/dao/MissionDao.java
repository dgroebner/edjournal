package de.dgroebner.edjson.db.dao;

import static de.dgroebner.edjson.db.mapper.AbstractMapper.COLUMN_JOURNAL_ID;
import static de.dgroebner.edjson.db.mapper.MissionMapper.COLUMN_COMMODITY;
import static de.dgroebner.edjson.db.mapper.MissionMapper.COLUMN_COMMODITY_COUNT;
import static de.dgroebner.edjson.db.mapper.MissionMapper.COLUMN_DESTINATION;
import static de.dgroebner.edjson.db.mapper.MissionMapper.COLUMN_DESTINATION_PORT;
import static de.dgroebner.edjson.db.mapper.MissionMapper.COLUMN_EXPIRY;
import static de.dgroebner.edjson.db.mapper.MissionMapper.COLUMN_FACTION_ID;
import static de.dgroebner.edjson.db.mapper.MissionMapper.COLUMN_FINANCE_ID;
import static de.dgroebner.edjson.db.mapper.MissionMapper.COLUMN_MISSION_ID;
import static de.dgroebner.edjson.db.mapper.MissionMapper.COLUMN_NAME;
import static de.dgroebner.edjson.db.mapper.MissionMapper.COLUMN_PASSENGER_COUNT;
import static de.dgroebner.edjson.db.mapper.MissionMapper.COLUMN_PASSENGER_TYPE;
import static de.dgroebner.edjson.db.mapper.MissionMapper.COLUMN_PASSENGER_VIP;
import static de.dgroebner.edjson.db.mapper.MissionMapper.COLUMN_PASSENGER_WANTED;
import static de.dgroebner.edjson.db.mapper.MissionMapper.COLUMN_REWARD;
import static de.dgroebner.edjson.db.mapper.MissionMapper.COLUMN_STATUS;
import static de.dgroebner.edjson.db.mapper.MissionMapper.COLUMN_TARGET_FACTION;

import java.time.LocalDateTime;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.binder.LocalDateTimeBinder;
import de.dgroebner.edjson.db.mapper.MissionMapper;

/**
 * DAO-Interface für die Tabelle 'mission'
 * 
 * @author dgroebner
 */
@RegisterMapper(MissionMapper.class)
public interface MissionDao extends AbstractDao {

    /**
     * Fügt eine neue Mission ein
     * 
     * @param journalId int
     * @param missionId int
     * @param factionId int
     * @param name {@link String}
     * @param commodity {@link String}
     * @param commodityCount int
     * @param passenger {@link String}
     * @param passengerCount int
     * @param passengerVip boolean
     * @param passengerWanted boolea
     * @param destination {@link String}
     * @param destinationPort {@link String}
     * @param targetFaction {@link String}
     * @param expiry {@link LocalDateTime}
     * @param status {@link String}
     * @return int
     */
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO mission (journal_id, missionId, faction_id, name, commodity, commodity_count, passenger_type, passenger_count, passenger_vip, passenger_wanted, destination, destination_port, target_faction, expiry, status) VALUES (:journal_id, :missionId, :faction_id, :name, :commodity, :commodity_count, :passenger_type, :passenger_count, :passenger_vip, :passenger_wanted, :destination, :destination_port, :target_faction, :expiry, :status)")
    @SuppressWarnings("squid:S00107")
    int insert(@Bind(COLUMN_JOURNAL_ID) int journalId, @Bind(COLUMN_MISSION_ID) int missionId,
            @Bind(COLUMN_FACTION_ID) int factionId, @Bind(COLUMN_NAME) String name,
            @Bind(COLUMN_COMMODITY) String commodity, @Bind(COLUMN_COMMODITY_COUNT) int commodityCount,
            @Bind(COLUMN_PASSENGER_TYPE) String passenger, @Bind(COLUMN_PASSENGER_COUNT) int passengerCount,
            @Bind(COLUMN_PASSENGER_VIP) boolean passengerVip, @Bind(COLUMN_PASSENGER_WANTED) boolean passengerWanted,
            @Bind(COLUMN_DESTINATION) String destination, @Bind(COLUMN_DESTINATION_PORT) String destinationPort,
            @Bind(COLUMN_TARGET_FACTION) String targetFaction,
            @Bind(value = COLUMN_EXPIRY, binder = LocalDateTimeBinder.class) LocalDateTime expiry,
            @Bind(COLUMN_STATUS) String status);
    
    /**
     * Aktualisiert die MetaDaten des Sternenhafens
     * 
     * @param status {@link String}
     * @param missionId int
     */
    @SqlUpdate("UPDATE mission SET status = :status WHERE missionId = :missionId")
    void updateStatus(@Bind(COLUMN_STATUS) String status, @Bind(COLUMN_MISSION_ID) int missionId);

    /**
     * Aktualisiert die MetaDaten des Sternenhafens
     * 
     * @param financeId int
     * @param reward int
     * @param missionId int
     */
    @SqlUpdate("UPDATE mission SET finance_id = :finance_id, reward = :reward WHERE missionId = :missionId")
    void updateFinanceId(@Bind(COLUMN_FINANCE_ID) int financeId, @Bind(COLUMN_REWARD) int reward,
            @Bind(COLUMN_MISSION_ID) int missionId);

}
