package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.Interdicted.Fields.FACTION;
import static de.dgroebner.edjson.model.data.Interdicted.Fields.INTERDICTOR;
import static de.dgroebner.edjson.model.data.Interdicted.Fields.TIMESTAMP;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Combatlog;
import de.dgroebner.edjson.db.Combatlog.ACTION;
import de.dgroebner.edjson.model.data.Interdicted;

/**
 * Action für das JournalModell {@link Interdicted}
 * 
 * @author dgroebner
 */
public class InterdictedAction extends AbstractAction<Interdicted> {

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final Interdicted model) {
        new Combatlog(dbi).save(journalId, model.getValueAsLocalDateTime(TIMESTAMP), ACTION.INTERDICTED,
                model.getValueAsString(INTERDICTOR), model.getValueAsString(FACTION), 0);
    }

}
