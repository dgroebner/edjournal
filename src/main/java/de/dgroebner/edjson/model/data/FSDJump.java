package de.dgroebner.edjson.model.data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.model.GenericModel;
import de.dgroebner.edjson.model.JournalModelField;

/**
 * Modellklasse für den Elite Dangerous Journaleintrag 'FSDJump'
 * 
 * @author dgroebner
 */
public class FSDJump extends GenericModel<FSDJump.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FSDJump.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'FSDJump'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        TIMESTAMP("timestamp", LocalDateTime.class),
        EVENT("event", String.class),
        STAR_SYSTEM("StarSystem", String.class),
        STAR_POS("StarPos", Coordinates.class),
        SYSTEM_ALLEGIANCE("SystemAllegiance", String.class),
        SYSTEM_ECONOMY("SystemEconomy", String.class),
        SYSTEM_ECONOMY_LOCALISED("SystemEconomy_Localised", String.class),
        SYSTEM_GOVERNMENT("SystemGovernment", String.class),
        SYSTEM_GOVERNMENT_LOCALISED("SystemGovernment_Localised", String.class),
        SYSTEM_SECURITY("SystemSecurity", String.class),
        SYSTEM_SECURITY_LOCALISED("SystemSecurity_Localised", String.class),
        JUMP_DIST("JumpDist", BigDecimal.class),
        FUEL_USED("FuelUsed", BigDecimal.class),
        FUEL_LEVEL("FuelLevel", BigDecimal.class),
        BOOST_USED("BoostUsed", Boolean.class),
        SYSTEM_FACTION("SystemFaction", String.class),
        FACTION_STATE("FactionState", String.class);
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
    public FSDJump(final JSONObject json) {
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
        return String.format("FSD-Sprung nach %s (%s) Distanz: %sly", getValueAsString(Fields.STAR_SYSTEM),
                getValueAsCoordinates(Fields.STAR_POS).getCoordString(), getValueAsBigDecimal(Fields.JUMP_DIST));
    }
}
