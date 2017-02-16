package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.CommunityGoalReward.Fields.REWARD;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Faction;
import de.dgroebner.edjson.db.Financedata;
import de.dgroebner.edjson.db.Financedata.CATEGORY;
import de.dgroebner.edjson.db.Properties;
import de.dgroebner.edjson.db.Properties.ENTRIES;
import de.dgroebner.edjson.db.model.DBFaction;
import de.dgroebner.edjson.model.data.CommunityGoalReward;

/**
 * Action für das JournalModell {@link CommunityGoalReward}
 * 
 * @author dgroebner
 */
public class CommunityGoalRewardAction extends AbstractAction<CommunityGoalReward> {

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final CommunityGoalReward model) {

        final int reward = model.getValueAsInt(REWARD);
        if (reward > 0) {
            final DBFaction faction = new Faction(dbi).getById(Integer.parseInt(new Properties(dbi)
                    .getValueForEntry(ENTRIES.CURRENT_LOCAL_FACTION)));
            new Financedata(dbi).save(journalId, model.getTimestamp(), reward, CATEGORY.MISSIONS,
                    "CommunitiyGoal Belohnung", faction.getName(), null);
        }
    }

}
