package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.BuyExplorationData.Fields.COST;
import static de.dgroebner.edjson.model.data.BuyExplorationData.Fields.SYSTEM;
import static de.dgroebner.edjson.model.data.BuyExplorationData.Fields.TIMESTAMP;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Financedata;
import de.dgroebner.edjson.db.Financedata.CATEGORY;
import de.dgroebner.edjson.model.data.BuyAmmo;
import de.dgroebner.edjson.model.data.BuyExplorationData;

/**
 * Action für das JournalModell {@link BuyAmmo}
 * 
 * @author dgroebner
 */
public class BuyExplorationDataAction extends AbstractAction<BuyExplorationData> {

    @Override
    protected String buildJournalMessage(final DBI dbi, final BuyExplorationData model) {
        return String.format("Erkundungsdaten für System %s gekauft", model.getValueAsString(SYSTEM));
    }

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final BuyExplorationData model) {
        new Financedata(dbi).save(journalId, model.getValueAsLocalDateTime(TIMESTAMP), model.getValueAsInt(COST) * -1,
                CATEGORY.EXPLORATION_DATA,
                String.format("Erkundungsdaten für System %s gekauft", model.getValueAsString(SYSTEM)));
    }

}
