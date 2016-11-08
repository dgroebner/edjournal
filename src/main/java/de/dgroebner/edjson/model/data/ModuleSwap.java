package de.dgroebner.edjson.model.data;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.model.GenericModel;
import de.dgroebner.edjson.model.JournalModelField;

/**
 * Modellklasse für den Elite Dangerous Journaleintrag 'ModuleSwap'
 * 
 * @author dgroebner
 */
public class ModuleSwap extends GenericModel<ModuleSwap.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModuleSwap.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'ModuleSwap'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        TIMESTAMP("timestamp", LocalDateTime.class),
        EVENT("event", String.class),
        FROM_SLOT("FromSlot", String.class),
        FROM_ITEM("FromItem", String.class),
        FROM_ITEM_LOCALISED("FromItem_Localised", String.class),
        TO_SLOT("ToSlot", String.class),
        TO_ITEM("ToItem", String.class),
        TO_ITEM_LOCALISED("ToItem_Localised", String.class),
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
    public ModuleSwap(final JSONObject json) {
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
