package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.BuyAmmo.Fields.COST;
import static de.dgroebner.edjson.model.data.BuyAmmo.Fields.TIMESTAMP;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Finanzdata;
import de.dgroebner.edjson.db.Finanzdata.CATEGORY;
import de.dgroebner.edjson.db.Ship;
import de.dgroebner.edjson.db.model.DBShip;
import de.dgroebner.edjson.model.data.BuyAmmo;

/**
 * Action für das JournalModell {@link BuyAmmo}
 * 
 * @author dgroebner
 */
public class BuyAmmoAction extends AbstractAction<BuyAmmo> {

    @Override
    protected String buildJournalMessage(final DBI dbi, final BuyAmmo model) {
        final DBShip ship = new Ship(dbi).getCurrentShip();
        final String callsign = StringUtils.defaultString(ship.getCallsign(), "unbenannt");

        return String.format("Munition für Schiff %s (%s) gekauft", callsign, ship.getType());
    }

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final BuyAmmo model) {
        new Finanzdata(dbi).save(journalId, model.getValueAsLocalDateTime(TIMESTAMP), model.getValueAsInt(COST) * -1,
                CATEGORY.OPERATING_COSTS, "Munition gekauft.");
    }

}
