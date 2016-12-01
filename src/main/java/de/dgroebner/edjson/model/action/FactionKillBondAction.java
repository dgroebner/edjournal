package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.FactionKillBond.Fields.TIMESTAMP;
import static de.dgroebner.edjson.model.data.FactionKillBond.Fields.VICTIM_FACTION;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Combatlog;
import de.dgroebner.edjson.db.Combatlog.ACTION;
import de.dgroebner.edjson.model.data.Bounty;
import de.dgroebner.edjson.model.data.FactionKillBond;

/**
 * Action für das JournalModell {@link Bounty}
 * 
 * @author dgroebner
 */
public class FactionKillBondAction extends AbstractAction<FactionKillBond> {

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final FactionKillBond model) {
        new Combatlog(dbi).save(journalId, model.getValueAsLocalDateTime(TIMESTAMP), ACTION.FIGHT_WON, null,
                model.getValueAsString(VICTIM_FACTION), 0);
    }

}
