package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.BuyDrones.Fields.COUNT;
import static de.dgroebner.edjson.model.data.BuyDrones.Fields.TIMESTAMP;
import static de.dgroebner.edjson.model.data.BuyDrones.Fields.TOTAL_COST;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Financedata;
import de.dgroebner.edjson.db.Financedata.CATEGORY;
import de.dgroebner.edjson.db.Ship;
import de.dgroebner.edjson.db.model.DBShip;
import de.dgroebner.edjson.model.data.BuyDrones;

/**
 * Action für das JournalModell {@link BuyDrones}
 * 
 * @author dgroebner
 */
public class BuyDronesAction extends AbstractAction<BuyDrones> {

    @Override
    protected String buildJournalMessage(final DBI dbi, final BuyDrones model) {
        final DBShip ship = new Ship(dbi).getCurrentShip();
        final String callsign = StringUtils.defaultString(ship.getCallsign(), "unbenannt");

        return String.format("%s Drohnen für Schiff %s (%s) gekauft", Integer.toString(model.getValueAsInt(COUNT)),
                callsign, ship.getType());
    }

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final BuyDrones model) {
        final String message = String.format("%s Drohnen gekauft.", Integer.toString(model.getValueAsInt(COUNT)));
        new Financedata(dbi).save(journalId, model.getValueAsLocalDateTime(TIMESTAMP), model.getValueAsInt(TOTAL_COST)
                * -1, CATEGORY.OPERATING_COSTS, message);
    }

}
