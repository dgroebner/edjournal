package de.dgroebner.edjson.model.data;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.model.GenericModel;
import de.dgroebner.edjson.model.JournalModelField;

/**
 * Modellklasse für den Elite Dangerous Journaleintrag 'Screenshot'
 * 
 * @author dgroebner
 */
public class Screenshot extends GenericModel<Screenshot.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Screenshot.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'Screenshot'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        TIMESTAMP("timestamp", LocalDateTime.class),
        EVENT("event", String.class),
        FILENAME("Filename", String.class),
        WIDTH("Width", Integer.class),
        HEIGHT("Height", Integer.class),
        SYSTEM("System", String.class),
        BODY("Body", String.class);
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
    public Screenshot(final JSONObject json) {
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
        return String.format("Foto im System %s in der Nähe von %s erstellt", getValueAsString(Fields.SYSTEM),
                getValueAsString(Fields.BODY));
    }

}
