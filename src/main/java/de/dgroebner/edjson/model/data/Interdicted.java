package de.dgroebner.edjson.model.data;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.model.GenericModel;
import de.dgroebner.edjson.model.JournalModelField;

/**
 * Modellklasse für den Elite Dangerous Journaleintrag 'Interdicted'
 * 
 * @author dgroebner
 */
public class Interdicted extends GenericModel<Interdicted.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Interdicted.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'Interdicted'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        TIMESTAMP("timestamp", LocalDateTime.class),
        EVENT("event", String.class),
        SUBMITTED("Submitted", Boolean.class),
        INTERDICTOR("Interdictor", String.class),
        IS_PLAYER("IsPlayer", Boolean.class),
        COMBAT_RANK("CombatRank", Integer.class),
        FACTION("Faction", String.class),
        POWER("Power", String.class);
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
    public Interdicted(final JSONObject json) {
        super(json, Arrays.asList(Fields.values()));
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    /**
     * Liefet den Kampfrang zurück
     * 
     * @return {@link RankCombat}
     */
    public RankCombat getCombatRank() {
        return getDataObject().has(Fields.COMBAT_RANK.getCode()) ? RankCombat
                .forCode(getValueAsInt(Fields.COMBAT_RANK)) : null;
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
