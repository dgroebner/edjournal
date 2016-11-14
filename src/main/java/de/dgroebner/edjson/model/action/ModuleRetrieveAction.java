package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.ModuleRetrieve.Fields.COST;
import static de.dgroebner.edjson.model.data.ModuleRetrieve.Fields.RETRIEVED_ITEM_LOCALISED;
import static de.dgroebner.edjson.model.data.ModuleRetrieve.Fields.SHIP;
import static de.dgroebner.edjson.model.data.ModuleRetrieve.Fields.SHIP_ID;
import static de.dgroebner.edjson.model.data.ModuleRetrieve.Fields.SWAP_OUT_ITEM_LOCALISED;
import static de.dgroebner.edjson.model.data.ModuleRetrieve.Fields.TIMESTAMP;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Finanzdata;
import de.dgroebner.edjson.db.Finanzdata.CATEGORY;
import de.dgroebner.edjson.db.Ship;
import de.dgroebner.edjson.db.model.DBShip;
import de.dgroebner.edjson.model.data.ModuleRetrieve;

/**
 * Action für das JournalModell {@link ModuleRetrieve}
 * 
 * @author dgroebner
 */
public class ModuleRetrieveAction extends AbstractAction<ModuleRetrieve> {

    @Override
    protected String buildJournalMessage(final DBI dbi, final ModuleRetrieve model) {
        final DBShip ship = new Ship(dbi).saveAndGetShip(model.getValueAsInt(SHIP_ID), model.getValueAsString(SHIP));
        final String callsign = StringUtils.defaultString(ship.getCallsign(), "unbenannt");
        
        final StringBuilder message = new StringBuilder();
        message.append(String.format("Modul %s aus Lager in Schiff %s (%s) transferiert.",
                model.getValueAsString(RETRIEVED_ITEM_LOCALISED), callsign, ship.getType()));
        
        final String swapItem = model.getValueAsString(SWAP_OUT_ITEM_LOCALISED);
        if (StringUtils.isNotBlank(swapItem)) {
            message.append(String.format(" Modul %s eingelagert.", model.getValueAsString(RETRIEVED_ITEM_LOCALISED)));
        }
        

        return message.toString();
    }

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final ModuleRetrieve model) {
        final int cost = model.getValueAsInt(COST);
        if (cost != 0) {
            new Finanzdata(dbi).save(journalId, model.getValueAsLocalDateTime(TIMESTAMP), cost, CATEGORY.MODUL_STORAGE,
                    String.format("Modul %s aus Lager eingebaut", model.getValueAsString(RETRIEVED_ITEM_LOCALISED)));
        }
    }

}
