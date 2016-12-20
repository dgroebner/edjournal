package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.SellExplorationData.Fields.BASE_VALUE;
import static de.dgroebner.edjson.model.data.SellExplorationData.Fields.BONUS;
import static de.dgroebner.edjson.model.data.SellExplorationData.Fields.SYSTEMS;
import static de.dgroebner.edjson.model.data.SellExplorationData.Fields.TIMESTAMP;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Financedata;
import de.dgroebner.edjson.db.Financedata.CATEGORY;
import de.dgroebner.edjson.model.data.SellExplorationData;

/**
 * Action für das JournalModell {@link SellExplorationData}
 * 
 * @author dgroebner
 */
public class SellExplorationDataAction extends AbstractAction<SellExplorationData> {

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final SellExplorationData model) {
        new Financedata(dbi).save(
                journalId,
                model.getValueAsLocalDateTime(TIMESTAMP),
                model.getValueAsInt(BASE_VALUE),
                CATEGORY.EXPLORATION_DATA,
                String.format("Erkundungsdaten für %s Systeme verkauft",
                        Integer.valueOf(model.getValueAsJsonArray(SYSTEMS).length())));

        final int bonus = model.getValueAsInt(BONUS);
        if (bonus > 0) {
            new Financedata(dbi).save(journalId, model.getValueAsLocalDateTime(TIMESTAMP),
                    model.getValueAsInt(BASE_VALUE), CATEGORY.EXPLORATION_DATA, "Bonus für Erstentdeckungen");
        }
    }

}
