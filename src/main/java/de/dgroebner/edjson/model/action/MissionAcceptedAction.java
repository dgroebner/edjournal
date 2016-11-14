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

        final DBMission mission = new DBMission();
        mission.setMissionId(model.getValueAsInt(MISSION_ID));
        mission.setFactionId(factionId);
        mission.setName(model.getValueAsString(NAME));
        mission.setCommodity(model.getValueAsString(COMMODITY_LOCALISED));
        mission.setCommodityCount(model.getValueAsInt(COUNT));
        mission.setPassengerType(model.getValueAsString(PASSENGER_TYPE));
        mission.setPassengerCount(model.getValueAsInt(PASSENGER_COUNT));
        mission.setPassengerVip(model.getValueAsBoolean(PASSENGER_VIPS));
        mission.setPassengerWanted(model.getValueAsBoolean(PASSENGER_WANTED));
        mission.setDestination(model.getValueAsString(DESTINATION_SYSTEM));
        mission.setDestinationPort(model.getValueAsString(DESTINATION_STATION));
        mission.setExpiry(model.getValueAsLocalDateTime(EXPIRY));
        new Mission(dbi).saveNew(journalId, mission);
    }

}
