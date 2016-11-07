package de.dgroebner.edjson.model.data;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.model.GenericModel;
import de.dgroebner.edjson.model.JournalModelField;

/**
 * Modellklasse für den Elite Dangerous Journaleintrag 'ShipyardBuy'
 * 
 * @author dgroebner
 */
public class ModuleBuy extends GenericModel<ModuleBuy.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModuleBuy.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'ShipyardBuy'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        TIMESTAMP("timestamp", LocalDateTime.class),
        EVENT("event", String.class),
        SLOT("Slot", String.class),
        BUY_ITEM("BuyItem", String.class),
        BUY_ITEM_LOCALISED("BuyItem_Localised", String.class),
        BUY_PRICE("BuyPrice", Integer.class),
        SHIP("Ship", String.class),
        SHIP_ID("ShipID", Integer.class),
        SELL_ITEM("SellItem", String.class),
        SELL_ITEM_LOCALISED("SellItem_Localised", String.class),
        SELL_PRICE("SellPrice", Integer.class);
        /* @formatter:on */

        private String code;

        private Class<?> type;

        Fields(final String code, final Class<?> type) {
            this.code = code;
            this.type = type;
        }

        @Override
        public String getCode() {
            return code;
        }

        @Override
        public Class<?> getType() {
            return type;
        }
    }

    /**
     * Erzeugt eine neue Dateiheader Modellklasse für das übergebene {@link JSONObject}
     * 
     * @param json {@link JSONObject}
     */
    public ModuleBuy(final JSONObject json) {
        super(json, Arrays.asList(Fields.values()));
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }
}
