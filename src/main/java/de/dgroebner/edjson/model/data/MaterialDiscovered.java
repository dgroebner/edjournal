package de.dgroebner.edjson.model.data;

import static de.dgroebner.edjson.model.data.MaterialDiscovered.Fields.CATEGORY;
import static de.dgroebner.edjson.model.data.MaterialDiscovered.Fields.DISCOVERY_NUMBER;
import static de.dgroebner.edjson.model.data.MaterialDiscovered.Fields.NAME;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.model.GenericModel;
import de.dgroebner.edjson.model.JournalModelField;

/**
 * Modellklasse für den Elite Dangerous Journaleintrag 'MaterialDiscovered'
 * 
 * @author dgroebner
 */
public class MaterialDiscovered extends GenericModel<MaterialDiscovered.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MaterialDiscovered.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'MaterialDiscovered'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        TIMESTAMP("timestamp", LocalDateTime.class),
        EVENT("event", String.class),
        CATEGORY("Category", String.class),
        NAME("Name", String.class),
        DISCOVERY_NUMBER("DiscoveryNumber", Integer.class);
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
    public MaterialDiscovered(final JSONObject json) {
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

    @Override
    public String getMessage() {
        return String.format("Neues Material %s der Kategorie %s gefunden. Anzahl: %d", getValueAsString(NAME),
                getValueAsString(CATEGORY), Integer.valueOf(getValueAsInt(DISCOVERY_NUMBER)));
    }
}
