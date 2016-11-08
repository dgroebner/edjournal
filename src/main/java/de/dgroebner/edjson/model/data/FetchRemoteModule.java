package de.dgroebner.edjson.model.data;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.model.GenericModel;
import de.dgroebner.edjson.model.JournalModelField;

/**
 * Modellklasse für den Elite Dangerous Journaleintrag 'FetchRemoteModule'
 * 
 * @author dgroebner
 */
public class FetchRemoteModule extends GenericModel<FetchRemoteModule.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FetchRemoteModule.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'FetchRemoteModule'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        TIMESTAMP("timestamp", LocalDateTime.class),
        EVENT("event", String.class),
        STORAGE_SLOT("StorageSlot", Integer.class),
        STORED_ITEM("StoredItem", String.class),
        STORED_ITEM_LOCALISED("StoredItem_Localised", String.class),
        SERVER_ID("ServerId", Integer.class),
        TRANSFER_COST("TransferCost", Integer.class),
        SHIP("Ship", String.class),
        SHIP_ID("ShipID", Integer.class);
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
    public FetchRemoteModule(final JSONObject json) {
        super(json, Arrays.asList(Fields.values()));
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return getValueAsLocalDateTime(Fields.TIMESTAMP);
    }

    @Override
    public String getEvent() {
        return getValueAsString(Fields.EVENT);
    }
}
