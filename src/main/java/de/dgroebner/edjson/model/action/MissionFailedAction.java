package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.MissionCompleted.Fields.MISSION_ID;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Mission;
import de.dgroebner.edjson.db.Mission.STATUS;
import de.dgroebner.edjson.model.data.MissionFailed;

/**
 * Action für das JournalModell {@link MissionFailed}
 * 
 * @author dgroebner
 */
public class MissionFailedAction extends AbstractAction<MissionFailed> {

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final MissionFailed model) {
        new Mission(dbi).updateStatus(model.getValueAsInt(MISSION_ID), STATUS.FAILED);
    }

}
