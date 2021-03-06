package de.dgroebner.edjson.model.data;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.apache.commons.lang3.text.translate.NumericEntityUnescaper.OPTION;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.model.GenericModel;
import de.dgroebner.edjson.model.JournalModelField;

/**
 * Modellklasse für den Elite Dangerous Journaleintrag 'Resurrect'
 * 
 * @author dgroebner
 */
public class Resurrect extends GenericModel<Resurrect.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Resurrect.class);

    /**
     * Resurrect Optionen
     */
    public enum OPTIONS {
        REBUY("rebuy");

        final String text;

        OPTIONS(final String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        /**
         * Gibt die Resurrect-Option für den übergebenen Text zurück
         * 
         * @param text {@link String}
         * @return {@link OPTION}
         */
        public static OPTIONS forText(final String text) {
            return Arrays
                    .asList(OPTIONS.values())
                    .stream()
                    .filter(option -> option.getText().equalsIgnoreCase(text))
                    .findFirst()
                    .orElseThrow(
                            () -> new IllegalArgumentException(String.format(
                                    "Text %s nicht als resurrect-Option erkannt", text)));
        }
    }

    /**
     * Felder des Elite Dangerous Journaleintrag 'Resurrect'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        TIMESTAMP("timestamp", LocalDateTime.class),
        EVENT("event", String.class),
        OPTION("Option", String.class),
        COST("Cost", Integer.class),
        BANKRUPT("Bankrupt", Boolean.class);
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
    public Resurrect(final JSONObject json) {
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
