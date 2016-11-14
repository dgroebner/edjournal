package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.ModuleSellRemote.Fields.SELL_ITEM_LOCALISED;
import static de.dgroebner.edjson.model.data.ModuleSellRemote.Fields.SELL_PRICE;
import static de.dgroebner.edjson.model.data.ModuleSellRemote.Fields.SHIP;
import static de.dgroebner.edjson.model.data.ModuleSellRemote.Fields.SHIP_ID;
import static de.dgroebner.edjson.model.data.ModuleSellRemote.Fields.TIMESTAMP;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Faction;
import de.dgroebner.edjson.db.Finanzdata;
import de.dgroebner.edjson.db.Finanzdata.CATEGORY;
import de.dgroebner.edjson.db.Ship;
import de.dgroebner.edjson.db.model.DBShip;
import de.dgroebner.edjson.model.data.ModuleSellRemote;

/**
 * Action für das JournalModell {@link ModuleSellRemote}
 * 
 * @author dgroebner
 */
public class ModuleSellRemoteAction extends AbstractAction<ModuleSellRemote> {

    @Override
    protected String buildJournalMessage(final DBI dbi, final ModuleSellRemote model) {
        final DBShip ship = new Ship(dbi).saveAndGetShip(model.getValueAsInt(SHIP_ID), model.getValueAsString(SHIP));
        final String callsign = StringUtils.defaultString(ship.getCallsign(), "unbenannt");
        
        return String.format("Modul %s für Schiff %s (%s) verkauft", model.getValueAsString(SELL_ITEM_LOCALISED),
                callsign, ship.getType());
    }

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final ModuleSellRemote model) {
        new Finanzdata(dbi).save(journalId, model.getValueAsLocalDateTime(TIMESTAMP), model.getValueAsInt(SELL_PRICE),
                CATEGORY.MODUL_COSTS, String.format("Modul %s verkauft", model.getValueAsString(SELL_ITEM_LOCALISED)),
                Faction.UNDEFINED, null);
    }

}
