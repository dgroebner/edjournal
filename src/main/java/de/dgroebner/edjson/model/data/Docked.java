package de.dgroebner.edjson.model.data;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.model.GenericModel;
import de.dgroebner.edjson.model.JournalModelField;

/**
 * Modellklasse für den Elite Dangerous Journaleintrag 'Docked'
 * 
 * @author dgroebner
 */
public class Docked extends GenericModel<Docked.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Docked.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'Docked'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        TIMESTAMP("timestamp", LocalDateTime.class),
        EVENT("event", String.class),
        STATION_NAME("StationName", String.class),
        STATION_TYPE("StationType", String.class),
        STAR_SYSTEM("StarSystem", String.class),
        COCKPIT_BREACH("CockpitBreach", Boolean.class),
        STATION_FACTION("StationFaction", String.class),
        FACTION_STATE("FactionState", String.class),
        STATION_ECONOMY("StationEconomy", String.class),
        STATION_ECONOMY_LOCALISED("StationEconomy_Localised", String.class),
        STATION_GOVERNMENT("StationGovernment", String.class),
        STATION_GOVERNMENT_LOCALISED("StationGovernment_Localised", String.class),
        SECURITY_LOCALISED("Security_Localised", String.class),
        STATION_ALLEGIANCE("StationAllegiance", String.class);
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
    public Docked(final JSONObject json) {
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
        return String.format("Gedockt an %s-Typ Raumhafen %s im System %s",
                StringUtils.defaultIfBlank(getValueAsString(Fields.STATION_TYPE), "PlanetaryPort"),
                getValueAsString(Fields.STATION_NAME), getValueAsString(Fields.STAR_SYSTEM));
    }
}
