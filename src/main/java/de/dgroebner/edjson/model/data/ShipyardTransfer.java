package de.dgroebner.edjson.model.data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.model.GenericModel;
import de.dgroebner.edjson.model.JournalModelField;

/**
 * Modellklasse für den Elite Dangerous Journaleintrag 'ShipyardTransfer'
 * 
 * @author dgroebner
 */
public class ShipyardTransfer extends GenericModel<ShipyardTransfer.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShipyardTransfer.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'ShipyardTransfer'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        TIMESTAMP("timestamp", LocalDateTime.class),
        EVENT("event", String.class),
        SHIP_TYPE("ShipType", String.class),
        SHIP_ID("ShipID", Integer.class),
        SLOT("Slot", String.class),
        SYSTEM("System", String.class),
        DISTANCE("Distance", BigDecimal.class),
        TRANSFER_PRICE("TransferPrice", Integer.class);
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
    public ShipyardTransfer(final JSONObject json) {
        super(json, Arrays.asList(Fields.values()));
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }
}
