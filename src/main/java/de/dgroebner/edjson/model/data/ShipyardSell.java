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
public class ShipyardSell extends GenericModel<ShipyardSell.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShipyardSell.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'ShipyardBuy'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        TIMESTAMP("timestamp", LocalDateTime.class),
        EVENT("event", String.class),
        SELL_SHIP_ID("SellShipID", Integer.class),
        SHIP_TYPE("ShipType", String.class),
        SHIP_PRICE("ShipPrice", Integer.class),
        SYSTEM("System", String.class);
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
    public ShipyardSell(final JSONObject json) {
        super(json, Arrays.asList(Fields.values()));
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }
}
