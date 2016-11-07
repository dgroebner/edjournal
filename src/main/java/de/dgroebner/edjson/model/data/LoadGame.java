package de.dgroebner.edjson.model.data;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.model.GenericModel;
import de.dgroebner.edjson.model.JournalModelField;

/**
 * Modellklasse für den Elite Dangerous Journaleintrag 'LoadGame'
 * 
 * @author dgroebner
 */
public class LoadGame extends GenericModel<LoadGame.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoadGame.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'LoadGame'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        TIMESTAMP("timestamp", LocalDateTime.class),
        EVENT("event", String.class),
        COMMANDER("Commander", String.class),
        SHIP("Ship", String.class),
        SHIP_ID("ShipID", Integer.class),
        START_LANDED("StartLanded", Boolean.class),
        START_DEAD("StartDead", Boolean.class),
        GAMEMODE("GameMode", String.class),
        GROUP("Group", String.class),
        CREDITS("Credits", Integer.class),
        LOAN("Loan", Integer.class);
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
    public LoadGame(final JSONObject json) {
        super(json, Arrays.asList(Fields.values()));
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

}
