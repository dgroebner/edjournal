package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.ShipyardTransfer.Fields.DISTANCE;
import static de.dgroebner.edjson.model.data.ShipyardTransfer.Fields.SHIP_ID;
import static de.dgroebner.edjson.model.data.ShipyardTransfer.Fields.SHIP_TYPE;
import static de.dgroebner.edjson.model.data.ShipyardTransfer.Fields.SYSTEM;
import static de.dgroebner.edjson.model.data.ShipyardTransfer.Fields.TIMESTAMP;
import static de.dgroebner.edjson.model.data.ShipyardTransfer.Fields.TRANSFER_PRICE;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Financedata;
import de.dgroebner.edjson.db.Financedata.CATEGORY;
import de.dgroebner.edjson.db.Properties;
import de.dgroebner.edjson.db.Ship;
import de.dgroebner.edjson.db.model.DBShip;
import de.dgroebner.edjson.model.data.ShipyardTransfer;

/**
 * Action für das JournalModell {@link ShipyardTransfer}
 * 
 * @author dgroebner
 */
public class ShipyardTransferAction extends AbstractAction<ShipyardTransfer> {

    @Override
    protected String buildJournalMessage(final DBI dbi, final ShipyardTransfer model) {
        final DBShip ship = new Ship(dbi).saveAndGetShip(model.getValueAsInt(SHIP_ID),
                model.getValueAsString(SHIP_TYPE));
        final String callsign = StringUtils.defaultString(ship.getCallsign(), "unbenannt");
        final String system = new Properties(dbi).getValueForEntry(Properties.ENTRIES.CURRENT_STAR_SYSTEM);
        
        return String.format("Schiffstransfer von Schiff %s (%s) von %s nach %s eingeleitet. Entfernung: %s ly.",
                callsign, ship.getType(), model.getValueAsString(SYSTEM), system, model.getValueAsBigDecimal(DISTANCE));
    }

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final ShipyardTransfer model) {
        final DBShip ship = new Ship(dbi).saveAndGetShip(model.getValueAsInt(SHIP_ID),
                model.getValueAsString(SHIP_TYPE));
        final String callsign = StringUtils.defaultString(ship.getCallsign(), "unbenannt");
        final int cost = model.getValueAsInt(TRANSFER_PRICE);
        if (cost != 0) {
            new Financedata(dbi).save(
                    journalId,
                    model.getValueAsLocalDateTime(TIMESTAMP),
                    cost * -1,
                    CATEGORY.TRANSFERS,
                    String.format("Schiffstransfer von Schiff %s (%s) über %s ly", callsign, ship.getType(),
                            model.getValueAsBigDecimal(DISTANCE)));
        }
    }

}
