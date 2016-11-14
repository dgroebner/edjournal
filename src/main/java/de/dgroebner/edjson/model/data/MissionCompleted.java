package de.dgroebner.edjson.model.data;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.model.GenericModel;
import de.dgroebner.edjson.model.JournalModelField;

/**
 * Modellklasse für den Elite Dangerous Journaleintrag 'MissionCompleted'
 * 
 * @author dgroebner
 */
public class MissionCompleted extends GenericModel<MissionCompleted.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MissionCompleted.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'MissionCompleted'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        TIMESTAMP("timestamp", LocalDateTime.class),
        EVENT("event", String.class),
        FACTION("Faction", String.class),
        NAME("Name", String.class),
        DONATION("Donation", Integer.class),
        COMMODITY("Commodity", String.class),
        COMMODITY_LOCALISED("Commodity_Localised", String.class),
        DESTINATION_SYSTEM("DestinationSystem", String.class),
        DESTINATION_STATION("DestinationStation", String.class),
        REWARD("Reward", Integer.class),
        MISSION_ID("MissionID", Integer.class);
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
    public MissionCompleted(final JSONObject json) {
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
        return String.format("Mission bei %s abgeschlossen", getValueAsString(Fields.FACTION));
    }
}
