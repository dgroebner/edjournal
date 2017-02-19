package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.MissionAccepted.Fields.COMMODITY_LOCALISED;
import static de.dgroebner.edjson.model.data.MissionAccepted.Fields.COUNT;
import static de.dgroebner.edjson.model.data.MissionAccepted.Fields.DESTINATION_STATION;
import static de.dgroebner.edjson.model.data.MissionAccepted.Fields.DESTINATION_SYSTEM;
import static de.dgroebner.edjson.model.data.MissionAccepted.Fields.EXPIRY;
import static de.dgroebner.edjson.model.data.MissionAccepted.Fields.FACTION;
import static de.dgroebner.edjson.model.data.MissionAccepted.Fields.MISSION_ID;
import static de.dgroebner.edjson.model.data.MissionAccepted.Fields.NAME;
import static de.dgroebner.edjson.model.data.MissionAccepted.Fields.PASSENGER_COUNT;
import static de.dgroebner.edjson.model.data.MissionAccepted.Fields.PASSENGER_TYPE;
import static de.dgroebner.edjson.model.data.MissionAccepted.Fields.PASSENGER_VIPS;
import static de.dgroebner.edjson.model.data.MissionAccepted.Fields.PASSENGER_WANTED;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Faction;
import de.dgroebner.edjson.db.Mission;
import de.dgroebner.edjson.db.model.DBMission;
import de.dgroebner.edjson.model.data.MissionAccepted;

/**
 * Action für das JournalModell {@link MissionAccepted}
 * 
 * @author dgroebner
 */
public class MissionAcceptedAction extends AbstractAction<MissionAccepted> {

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final MissionAccepted model) {
        final int factionId = new Faction(dbi).writeOrGetFraction(journalId, model.getValueAsString(FACTION)).getId();

        /* @formatter:off */
        final DBMission mission = DBMission.builder()
                .missionId(model.getValueAsInt(MISSION_ID))
                .factionId(factionId)
                .name(model.getValueAsString(NAME))
                .commodity(model.getValueAsString(COMMODITY_LOCALISED))
                .commodityCount(model.getValueAsInt(COUNT))
                .passengerType(model.getValueAsString(PASSENGER_TYPE))
                .passengerCount(model.getValueAsInt(PASSENGER_COUNT))
                .passengerVip(model.getValueAsBoolean(PASSENGER_VIPS))
                .passengerWanted(model.getValueAsBoolean(PASSENGER_WANTED))
                .destination(model.getValueAsString(DESTINATION_SYSTEM))
                .destinationPort(model.getValueAsString(DESTINATION_STATION))
                .expiry(model.getValueAsLocalDateTime(EXPIRY))
                .build();
        /* @formatter:on */

        new Mission(dbi).saveNew(journalId, mission);
    }

}
