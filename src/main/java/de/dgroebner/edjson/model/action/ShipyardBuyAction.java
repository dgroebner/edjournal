package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.ShipyardBuy.Fields.SELL_OLD_SHIP;
import static de.dgroebner.edjson.model.data.ShipyardBuy.Fields.SELL_PRICE;
import static de.dgroebner.edjson.model.data.ShipyardBuy.Fields.SELL_SHIP_ID;
import static de.dgroebner.edjson.model.data.ShipyardBuy.Fields.SHIP_PRICE;
import static de.dgroebner.edjson.model.data.ShipyardBuy.Fields.SHIP_TYPE;
import static de.dgroebner.edjson.model.data.ShipyardBuy.Fields.STORE_OLD_SHIP;
import static de.dgroebner.edjson.model.data.ShipyardBuy.Fields.STORE_SHIP_ID;
import static de.dgroebner.edjson.model.data.ShipyardBuy.Fields.TIMESTAMP;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Finanzdata;
import de.dgroebner.edjson.db.Finanzdata.CATEGORY;
import de.dgroebner.edjson.db.Ship;
import de.dgroebner.edjson.db.model.DBShip;
import de.dgroebner.edjson.model.data.ShipyardBuy;

/**
 * Action für das JournalModell {@link ShipyardBuy}
 * 
 * @author dgroebner
 */
public class ShipyardBuyAction extends AbstractAction<ShipyardBuy> {

    @Override
    protected String buildJournalMessage(final DBI dbi, final ShipyardBuy model) {
        final String oldShipType = StringUtils.defaultIfBlank(model.getValueAsString(STORE_OLD_SHIP), model.getValueAsString(SELL_OLD_SHIP));
        final int oldShipId = model.getValueAsInt(STORE_SHIP_ID) != 0 ? model.getValueAsInt(STORE_SHIP_ID) : model
                .getValueAsInt(SELL_SHIP_ID);
        
        final DBShip oldShip = new Ship(dbi).saveAndGetShip(oldShipId, oldShipType);
        final String callsign = StringUtils.defaultString(oldShip.getCallsign(), "unbenannt");

        final String action = StringUtils.isBlank(model.getValueAsString(STORE_OLD_SHIP)) ? "verkauft" : "eingelagert";
        return String.format("Neues Schiff vom Typ %s gekauft. Altes Schiff %s (%s) %s",
                model.getValueAsString(SHIP_TYPE), callsign, oldShipType, action);
    }

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final ShipyardBuy model) {
        final int sellprice = model.getValueAsInt(SELL_PRICE);
        if (sellprice != 0) {
            final DBShip oldShip = new Ship(dbi).saveAndGetShip(model.getValueAsInt(SELL_SHIP_ID),
                    model.getValueAsString(SELL_OLD_SHIP));
            final String callsign = StringUtils.defaultString(oldShip.getCallsign(), "unbenannt");
            new Finanzdata(dbi).save(journalId, model.getValueAsLocalDateTime(TIMESTAMP), sellprice,
                    CATEGORY.SHIP_COSTS, String.format("Schiff %s (%s) verkauft", callsign, oldShip.getType()));

            new Ship(dbi).delete(oldShip.getEdId());
        }

        new Finanzdata(dbi)
                .save(journalId, model.getValueAsLocalDateTime(TIMESTAMP), model.getValueAsInt(SHIP_PRICE) * -1,
                        CATEGORY.SHIP_COSTS,
                        String.format("Schiff vom Typ %s gekauft", model.getValueAsString(SHIP_TYPE)));
    }

}
