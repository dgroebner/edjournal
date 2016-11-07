package de.dgroebner.edjson.model.data;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.model.GenericModel;
import de.dgroebner.edjson.model.JournalModelField;

/**
 * Modellklasse für den Elite Dangerous Journaleintrag 'Location'
 * 
 * @author dgroebner
 */
public class Location extends GenericModel<Location.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Location.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'Location'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        TIMESTAMP("timestamp", LocalDateTime.class),
        EVENT("event", String.class),
        DOCKED("Docked", Boolean.class),
        STATION_NAME("StationName", String.class),
        STATION_TYPE("StationType", String.class),
        STAR_SYSTEM("StarSystem", String.class),
        STAR_POS("StarPos", Coordinates.class),
        BODY("Body", String.class),
        BODY_TYPE("BodyType", String.class),
        FACTION("Faction", String.class),
        FACTION_STATE("FactionState", String.class),
        ALLEGIANCE("Allegiance", String.class),
        ECONOMY("Economy", String.class),
        ECONOMY_LOCALISED("Economy_Localised", String.class),
        GOVERNMENT("Government", String.class),
        GOVERNMENT_LOCALISED("Government_Localised", String.class),
        SECURITY_LOCALISED("Security", String.class);
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
    public Location(final JSONObject json) {
        super(json, Arrays.asList(Fields.values()));
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }
}
