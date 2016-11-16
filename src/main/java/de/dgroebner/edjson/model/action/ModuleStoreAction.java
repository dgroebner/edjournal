package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.ModuleStore.Fields.COST;
import static de.dgroebner.edjson.model.data.ModuleStore.Fields.SHIP;
import static de.dgroebner.edjson.model.data.ModuleStore.Fields.SHIP_ID;
import static de.dgroebner.edjson.model.data.ModuleStore.Fields.STORED_ITEM_LOCALISED;
import static de.dgroebner.edjson.model.data.ModuleStore.Fields.TIMESTAMP;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Financedata;
import de.dgroebner.edjson.db.Financedata.CATEGORY;
import de.dgroebner.edjson.db.Ship;
import de.dgroebner.edjson.db.model.DBShip;
import de.dgroebner.edjson.model.data.ModuleStore;

/**
 * Action für das JournalModell {@link ModuleStore}
 * 
 * @author dgroebner
 */
public class ModuleStoreAction extends AbstractAction<ModuleStore> {

    @Override
    protected String buildJournalMessage(final DBI dbi, final ModuleStore model) {
        final DBShip ship = new Ship(dbi).saveAndGetShip(model.getValueAsInt(SHIP_ID), model.getValueAsString(SHIP));
        final String callsign = StringUtils.defaultString(ship.getCallsign(), "unbenannt");

        return String.format("Modul %s aus Schiff %s (%s) eingelagert", model.getValueAsString(STORED_ITEM_LOCALISED),
                callsign, ship.getType());
    }

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final ModuleStore model) {
        final int cost = model.getValueAsInt(COST);
        if (cost != 0) {
            new Financedata(dbi).save(journalId, model.getValueAsLocalDateTime(TIMESTAMP), cost, CATEGORY.MODUL_STORAGE,
                    String.format("Modul %s eingelagert", model.getValueAsString(STORED_ITEM_LOCALISED)));
        }
    }

}
