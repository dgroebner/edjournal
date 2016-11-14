package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.ShipyardSwap.Fields.SHIP_ID;
import static de.dgroebner.edjson.model.data.ShipyardSwap.Fields.SHIP_TYPE;
import static de.dgroebner.edjson.model.data.ShipyardSwap.Fields.STORE_OLD_SHIP;
import static de.dgroebner.edjson.model.data.ShipyardSwap.Fields.STORE_SHIP_ID;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Properties;
import de.dgroebner.edjson.db.Properties.ENTRIES;
import de.dgroebner.edjson.db.Ship;
import de.dgroebner.edjson.db.model.DBShip;
import de.dgroebner.edjson.model.data.ShipyardSwap;

/**
 * Action für das JournalModell {@link ShipyardSwap}
 * 
 * @author dgroebner
 */
public class ShipyardSwapAction extends AbstractAction<ShipyardSwap> {

    @Override
    protected String buildJournalMessage(final DBI dbi, final ShipyardSwap model) {
        final DBShip dbShipNew = new Ship(dbi).saveAndGetShip(model.getValueAsInt(SHIP_ID),
                model.getValueAsString(SHIP_TYPE));
        final DBShip dbShipOld = new Ship(dbi).saveAndGetShip(model.getValueAsInt(STORE_SHIP_ID),
                model.getValueAsString(STORE_OLD_SHIP));
        new Properties(dbi).save(ENTRIES.CURRENT_SHIP, dbShipNew.getId());
        final String callsignNew = StringUtils.defaultString(dbShipNew.getCallsign(), "unbenannt");
        final String callsignOld = StringUtils.defaultString(dbShipOld.getCallsign(), "unbenannt");

        return String.format("Umstieg von Schiff %s (%s) auf Schiff %s (%s)", callsignOld,
                dbShipOld.getType(), callsignNew, dbShipNew.getType());
    }

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final ShipyardSwap model) {
        // do nothing
    }

}
