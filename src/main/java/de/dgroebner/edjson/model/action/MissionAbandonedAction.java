package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.MissionAbandoned.Fields.MISSION_ID;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Mission;
import de.dgroebner.edjson.db.Mission.STATUS;
import de.dgroebner.edjson.model.data.MissionAbandoned;

/**
 * Action für das JournalModell {@link MissionAbandoned}
 * 
 * @author dgroebner
 */
public class MissionAbandonedAction extends AbstractAction<MissionAbandoned> {

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final MissionAbandoned model) {
        final Mission mission = new Mission(dbi);
        mission.updateStatus(model.getValueAsInt(MISSION_ID), STATUS.ABANDONED);
    }

}
