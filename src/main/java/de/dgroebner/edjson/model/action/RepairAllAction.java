package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.RepairAll.Fields.COST;
import static de.dgroebner.edjson.model.data.RepairAll.Fields.TIMESTAMP;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Financedata;
import de.dgroebner.edjson.db.Financedata.CATEGORY;
import de.dgroebner.edjson.db.Ship;
import de.dgroebner.edjson.db.model.DBShip;
import de.dgroebner.edjson.model.data.RepairAll;

/**
 * Action für das JournalModell {@link RepairAll}
 * 
 * @author dgroebner
 */
public class RepairAllAction extends AbstractAction<RepairAll> {

    @Override
    protected String buildJournalMessage(final DBI dbi, final RepairAll model) {
        final DBShip ship = new Ship(dbi).getCurrentShip();
        final String callsign = StringUtils.defaultString(ship.getCallsign(), "unbenannt");

        return String.format("Schiff %s (%s) vollständig repariert", callsign, ship.getType());
    }

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final RepairAll model) {
        new Financedata(dbi).save(journalId, model.getValueAsLocalDateTime(TIMESTAMP), model.getValueAsInt(COST) * -1,
                CATEGORY.OPERATING_COSTS, "Schiff repariert.");
    }

}
