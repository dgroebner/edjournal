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
 * Modellklasse für den Elite Dangerous Journaleintrag 'RebootRepair'
 * 
 * @author dgroebner
 */
public class RebootRepair extends GenericModel<RebootRepair.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RebootRepair.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'RebootRepair'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        TIMESTAMP("timestamp", LocalDateTime.class),
        EVENT("event", String.class),
        MODULES("Modules", JSONArray.class);
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
    public RebootRepair(final JSONObject json) {
        super(json, Arrays.asList(Fields.values()));
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    /**
     * Liefert die reparierten Module zurück
     * 
     * @return {@link Set} {@link String}
     */
    public Set<String> getModules() {
        return getValueAsJsonArray(Fields.MODULES).toList().stream().map(value -> (String) value)
                .collect(Collectors.toSet());
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
