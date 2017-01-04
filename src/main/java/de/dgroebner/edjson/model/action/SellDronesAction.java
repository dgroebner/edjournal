package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.SellDrones.Fields.COUNT;
import static de.dgroebner.edjson.model.data.SellDrones.Fields.TIMESTAMP;
import static de.dgroebner.edjson.model.data.SellDrones.Fields.TOTAL_SALE;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Financedata;
import de.dgroebner.edjson.db.Financedata.CATEGORY;
import de.dgroebner.edjson.model.data.SellDrones;

/**
 * Action für das JournalModell {@link SellDrones}
 * 
 * @author dgroebner
 */
public class SellDronesAction extends AbstractAction<SellDrones> {

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final SellDrones model) {
        new Financedata(dbi).save(journalId, model.getValueAsLocalDateTime(TIMESTAMP), model.getValueAsInt(TOTAL_SALE),
                CATEGORY.OPERATING_COSTS,
                String.format("Drohnen verkauft. (%s Stück)", Integer.toString(model.getValueAsInt(COUNT))));
    }

}
