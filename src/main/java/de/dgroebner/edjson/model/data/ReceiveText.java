package de.dgroebner.edjson.model.data;

import static de.dgroebner.edjson.model.data.ReceiveText.Fields.FROM;
import static de.dgroebner.edjson.model.data.ReceiveText.Fields.FROM_LOCALISED;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.model.GenericModel;
import de.dgroebner.edjson.model.JournalModelField;

/**
 * Modellklasse für den Elite Dangerous Journaleintrag 'ReceiveText'
 * 
 * @author dgroebner
 */
public class ReceiveText extends GenericModel<ReceiveText.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiveText.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'ReceiveText'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        TIMESTAMP("timestamp", LocalDateTime.class),
        EVENT("event", String.class),
        FROM("From", String.class),
        FROM_LOCALISED("From_Localised", String.class),
        MESSAGE("Message", String.class),
        MESSAGE_LOCALISED("Message_Localised", String.class),
        CHANNEL("Channel", String.class);
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
    public ReceiveText(final JSONObject json) {
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
        String sender = StringUtils.defaultIfBlank(getValueAsString(FROM_LOCALISED), getValueAsString(FROM));
        sender = StringUtils.removeStart(sender, "&");
        return String.format("Nachricht von %s empfangen", sender);
    }
}
