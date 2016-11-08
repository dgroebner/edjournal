package de.dgroebner.edjson.model.data;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.model.GenericModel;
import de.dgroebner.edjson.model.JournalModelField;

/**
 * Modellklasse für den Elite Dangerous Journaleintrag 'Died'
 * 
 * @author dgroebner
 */
public class Died extends GenericModel<Died.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Died.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'Died'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        TIMESTAMP("timestamp", LocalDateTime.class),
        EVENT("event", String.class),
        KILLER_NAME("KillerName", String.class),
        KILLER_SHIP("KillerShip", String.class),
        KILLER_RANK("KillerRank", String.class),
        KILLERS("Killers", JSONArray.class);
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
    public Died(final JSONObject json) {
        super(json, Arrays.asList(Fields.values()));
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    /**
     * Liefert eine Liste der Killer, falls ein Wing den Tot verursacht hat.
     * 
     * @return {@link Set} of {@link Killer}
     */
    public Set<Killer> getKillers() {
        final Set<Killer> killers = new HashSet<>();
        getValueAsJsonArray(Fields.KILLERS).forEach(killer -> killers.add(new Killer((JSONObject) killer)));
        return killers;
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
