package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.PayLegacyFines.Fields.AMOUNT;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Faction;
import de.dgroebner.edjson.db.Finanzdata;
import de.dgroebner.edjson.db.Finanzdata.CATEGORY;
import de.dgroebner.edjson.model.data.PayLegacyFines;

/**
 * Action für das JournalModell {@link PayLegacyFines}
 * 
 * @author dgroebner
 */
public class PayLegacyFinesAction extends AbstractAction<PayLegacyFines> {

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final PayLegacyFines model) {
        new Finanzdata(dbi).save(journalId, model.getTimestamp(), model.getValueAsInt(AMOUNT) * -1, CATEGORY.FINES,
                "Strafen beglichen.", Faction.UNDEFINED, null);
    }

}
