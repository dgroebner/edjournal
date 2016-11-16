package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.ModuleBuy.Fields.BUY_ITEM_LOCALISED;
import static de.dgroebner.edjson.model.data.ModuleBuy.Fields.BUY_PRICE;
import static de.dgroebner.edjson.model.data.ModuleBuy.Fields.SELL_ITEM;
import static de.dgroebner.edjson.model.data.ModuleBuy.Fields.SELL_ITEM_LOCALISED;
import static de.dgroebner.edjson.model.data.ModuleBuy.Fields.SELL_PRICE;
import static de.dgroebner.edjson.model.data.ModuleBuy.Fields.SHIP;
import static de.dgroebner.edjson.model.data.ModuleBuy.Fields.SHIP_ID;
import static de.dgroebner.edjson.model.data.ModuleBuy.Fields.TIMESTAMP;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Financedata;
import de.dgroebner.edjson.db.Financedata.CATEGORY;
import de.dgroebner.edjson.db.Ship;
import de.dgroebner.edjson.db.model.DBShip;
import de.dgroebner.edjson.model.data.ModuleBuy;

/**
 * Action für das JournalModell {@link ModuleBuy}
 * 
 * @author dgroebner
 */
public class ModuleBuyAction extends AbstractAction<ModuleBuy> {

    @Override
    protected String buildJournalMessage(final DBI dbi, final ModuleBuy model) {
        final DBShip ship = new Ship(dbi).saveAndGetShip(model.getValueAsInt(SHIP_ID), model.getValueAsString(SHIP));
        final String callsign = StringUtils.defaultString(ship.getCallsign(), "unbenannt");
        
        StringBuilder message = new StringBuilder();
        message.append(String.format("Modul %s für Schiff %s (%s) gekauft.",
                model.getValueAsString(BUY_ITEM_LOCALISED),
                callsign, ship.getType()));
        final String sellItem = model.getValueAsString(SELL_ITEM);
        if (StringUtils.isNoneBlank(sellItem)) {
            message.append(String.format(" Modul %s verkauft", model.getValueAsString(SELL_ITEM_LOCALISED)));
        }
        
        return message.toString();
    }

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final ModuleBuy model) {
        final int cost = model.getValueAsInt(BUY_PRICE) * -1;
        if (cost != 0) {
            new Financedata(dbi).save(journalId, model.getValueAsLocalDateTime(TIMESTAMP), cost, CATEGORY.MODUL_COSTS,
                    String.format("Modul %s gekauft", model.getValueAsString(BUY_ITEM_LOCALISED)));
        }

        final int sellPrice = model.getValueAsInt(SELL_PRICE);
        if (sellPrice != 0) {
            new Financedata(dbi).save(journalId, model.getValueAsLocalDateTime(TIMESTAMP), sellPrice,
                    CATEGORY.MODUL_COSTS,
                    String.format("Modul %s verkauft", model.getValueAsString(SELL_ITEM_LOCALISED)));
        }
    }

}
