package de.dgroebner.edjson.model.data;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.db.Faction;
import de.dgroebner.edjson.model.GenericModel;
import de.dgroebner.edjson.model.JournalModelField;

/**
 * Modellklasse für den Elite Dangerous Journaleintrag 'FactionKillBond'
 * 
 * @author dgroebner
 */
public class FactionKillBond extends GenericModel<FactionKillBond.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FactionKillBond.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'FactionKillBond'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        TIMESTAMP("timestamp", LocalDateTime.class),
        EVENT("event", String.class),
        REWARD("Reward", Integer.class),
        AWARDING_FACTION("AwardingFaction", String.class),
        VICTIM_FACTION("VictimFaction", String.class);
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
    public FactionKillBond(final JSONObject json) {
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
        final String faction = StringUtils.isBlank(getValueAsString(Fields.VICTIM_FACTION)) ? Faction.UNDEFINED
                : getValueAsString(Fields.VICTIM_FACTION);
        return String
                .format("Kampfzonenbelohnung in Höhe von %scr für zerstörtes Schiff von Fraktion %s kann von %s eingefordert werden.",
                        Integer.valueOf(getValueAsInt(Fields.REWARD)), faction,
                        getValueAsString(Fields.AWARDING_FACTION));
    }
}
