package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.ShipyardNew.Fields.NEW_SHIP_ID;
import static de.dgroebner.edjson.model.data.ShipyardNew.Fields.SHIP_TYPE;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Properties;
import de.dgroebner.edjson.db.Properties.ENTRIES;
import de.dgroebner.edjson.db.Ship;
import de.dgroebner.edjson.db.model.DBShip;
import de.dgroebner.edjson.model.data.ShipyardNew;

/**
 * Action für das JournalModell {@link ShipyardNew}
 * 
 * @author dgroebner
 */
public class ShipyardNewAction extends AbstractAction<ShipyardNew> {

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final ShipyardNew model) {
        final DBShip dbShipNew = new Ship(dbi).saveAndGetShip(model.getValueAsInt(NEW_SHIP_ID),
                model.getValueAsString(SHIP_TYPE));
        new Properties(dbi).save(ENTRIES.CURRENT_SHIP, dbShipNew.getId());
    }

}
