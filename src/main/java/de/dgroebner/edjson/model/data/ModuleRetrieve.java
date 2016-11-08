package de.dgroebner.edjson.model.data;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.model.GenericModel;
import de.dgroebner.edjson.model.JournalModelField;

/**
 * Modellklasse für den Elite Dangerous Journaleintrag 'ModuleRetrieve'
 * 
 * @author dgroebner
 */
public class ModuleRetrieve extends GenericModel<ModuleRetrieve.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModuleRetrieve.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'ModuleRetrieve'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        TIMESTAMP("timestamp", LocalDateTime.class),
        EVENT("event", String.class),
        SLOT("Slot", String.class),
        RETRIEVED_ITEM("RetrievedItem", String.class),
        RETRIEVED_ITEM_LOCALISED("RetrievedItem_Localised", String.class),
        SHIP("Ship", String.class),
        SHIP_ID("ShipID", Integer.class),
        ENGINEER_MODIFICATIONS("EngineerModifications", String.class),
        SWAP_OUT_ITEM("SwapOutItem", String.class),
        SWAP_OUT_ITEM_LOCALISED("SwapOutItem_Localised", String.class),
        COST("Cost", Integer.class);
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
    public ModuleRetrieve(final JSONObject json) {
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
