package de.dgroebner.edjson.model.data;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.model.GenericModel;
import de.dgroebner.edjson.model.JournalModelField;

/**
 * Modellklasse für den Elite Dangerous Journaleintrag 'WingJoin'
 * 
 * @author dgroebner
 */
public class WingJoin extends GenericModel<WingJoin.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WingJoin.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'WingJoin'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        TIMESTAMP("timestamp", LocalDateTime.class),
        EVENT("event", String.class),
        OTHERS("Others", JSONArray.class);
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
    public WingJoin(final JSONObject json) {
        super(json, Arrays.asList(Fields.values()));
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    /**
     * Liefert ein Set der anderen Spieler im Wing
     * 
     * @return {@link Set} {@link String}
     */
    public Set<String> getOthers() {
        return getValueAsJsonArray(Fields.OTHERS).toList().stream().map(value -> (String) value)
                .collect(Collectors.toSet());
    }
}
