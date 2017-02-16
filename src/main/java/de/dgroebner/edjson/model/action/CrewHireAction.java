package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.CrewHire.Fields.COST;
import static de.dgroebner.edjson.model.data.CrewHire.Fields.NAME;
import static de.dgroebner.edjson.model.data.CrewHire.Fields.TIMESTAMP;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Financedata;
import de.dgroebner.edjson.db.Financedata.CATEGORY;
import de.dgroebner.edjson.model.data.CrewHire;

/**
 * Action für das JournalModell {@link CrewHire}
 * 
 * @author dgroebner
 */
public class CrewHireAction extends AbstractAction<CrewHire> {

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final CrewHire model) {
        final int cost = model.getValueAsInt(COST) * -1;
        if (cost != 0) {
            new Financedata(dbi).save(journalId, model.getValueAsLocalDateTime(TIMESTAMP), cost, CATEGORY.CREW_COSTS,
                    String.format("Crew-Mitglied %s angeheuert", model.getValueAsString(NAME)));
        }
    }

}
