package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.PayFines.Fields.AMOUNT;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Faction;
import de.dgroebner.edjson.db.Finanzdata;
import de.dgroebner.edjson.db.Finanzdata.CATEGORY;
import de.dgroebner.edjson.model.data.PayFines;

/**
 * Action für das JournalModell {@link PayFines}
 * 
 * @author dgroebner
 */
public class PayFinesAction extends AbstractAction<PayFines> {

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final PayFines model) {
        new Finanzdata(dbi).save(journalId, model.getTimestamp(), model.getValueAsInt(AMOUNT) * -1, CATEGORY.FINES,
                "Strafen beglichen.", Faction.UNDEFINED, null);
    }

}
