package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.MissionCompleted.Fields.DONATION;
import static de.dgroebner.edjson.model.data.MissionCompleted.Fields.FACTION;
import static de.dgroebner.edjson.model.data.MissionCompleted.Fields.MISSION_ID;
import static de.dgroebner.edjson.model.data.MissionCompleted.Fields.REWARD;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Faction;
import de.dgroebner.edjson.db.Financedata;
import de.dgroebner.edjson.db.Financedata.CATEGORY;
import de.dgroebner.edjson.db.Mission;
import de.dgroebner.edjson.db.Mission.STATUS;
import de.dgroebner.edjson.db.model.DBFaction;
import de.dgroebner.edjson.model.data.MissionAccepted;
import de.dgroebner.edjson.model.data.MissionCompleted;

/**
 * Action für das JournalModell {@link MissionAccepted}
 * 
 * @author dgroebner
 */
public class MissionCompletedAction extends AbstractAction<MissionCompleted> {

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final MissionCompleted model) {

        final Mission mission = new Mission(dbi);
        mission.updateStatus(model.getValueAsInt(MISSION_ID), STATUS.COMPLETED);

        final int reward = model.getValueAsInt(REWARD);
        if (reward > 0) {
            final DBFaction faction = new Faction(dbi).writeOrGetFraction(journalId, model.getValueAsString(FACTION));
            final int financeId = new Financedata(dbi).save(journalId, model.getTimestamp(), reward, CATEGORY.MISSIONS,
                    "Missionssold", faction.getName(), faction.getState());
            mission.updateFinanceId(model.getValueAsInt(MISSION_ID), financeId, reward);
        }

        final int donation = model.getValueAsInt(DONATION);
        if (donation > 0) {
            final DBFaction faction = new Faction(dbi).writeOrGetFraction(journalId, model.getValueAsString(FACTION));
            final int financeId = new Financedata(dbi).save(journalId, model.getTimestamp(), donation * -1,
                    CATEGORY.MISSIONS, "Spende geleistet", faction.getName(), faction.getState());
            mission.updateFinanceId(model.getValueAsInt(MISSION_ID), financeId, reward);
        }
    }

}
