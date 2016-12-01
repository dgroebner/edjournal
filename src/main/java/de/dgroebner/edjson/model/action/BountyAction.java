package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.Bounty.Fields.TIMESTAMP;
import static de.dgroebner.edjson.model.data.Bounty.Fields.VICTIM_FACTION;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Combatlog;
import de.dgroebner.edjson.db.Combatlog.ACTION;
import de.dgroebner.edjson.model.data.Bounty;

/**
 * Action für das JournalModell {@link Bounty}
 * 
 * @author dgroebner
 */
public class BountyAction extends AbstractAction<Bounty> {

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final Bounty model) {
        new Combatlog(dbi).save(journalId, model.getValueAsLocalDateTime(TIMESTAMP), ACTION.ENEMY_KILLED, null,
                model.getValueAsString(VICTIM_FACTION), 0);
    }

}
