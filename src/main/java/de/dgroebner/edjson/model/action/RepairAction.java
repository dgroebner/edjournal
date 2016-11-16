package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.Repair.Fields.COST;
import static de.dgroebner.edjson.model.data.Repair.Fields.ITEM;
import static de.dgroebner.edjson.model.data.Repair.Fields.TIMESTAMP;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Financedata;
import de.dgroebner.edjson.db.Financedata.CATEGORY;
import de.dgroebner.edjson.db.Ship;
import de.dgroebner.edjson.db.model.DBShip;
import de.dgroebner.edjson.model.data.Repair;

/**
 * Action für das JournalModell {@link Repair}
 * 
 * @author dgroebner
 */
public class RepairAction extends AbstractAction<Repair> {

    @Override
    protected String buildJournalMessage(final DBI dbi, final Repair model) {
        final DBShip ship = new Ship(dbi).getCurrentShip();
        final String callsign = StringUtils.defaultString(ship.getCallsign(), "unbenannt");

        return String.format("Modul %s von Schiff %s (%s) repariert", model.getValueAsString(ITEM), callsign,
                ship.getType());
    }

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final Repair model) {
        new Financedata(dbi).save(journalId, model.getValueAsLocalDateTime(TIMESTAMP), model.getValueAsInt(COST) * -1,
                CATEGORY.OPERATING_COSTS, "Schiff repariert.");
    }

}
