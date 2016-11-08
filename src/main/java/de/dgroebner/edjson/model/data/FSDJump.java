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
        ALLEGIANCE("Allegiance", String.class),
        ECONOMY("Economy", String.class),
        ECONOMY_LOCALISED("Economy_Localised", String.class),
        GOVERNMENT("Government", String.class),
        GOVERNMENT_LOCALISED("Government_Localised", String.class),
        SECURITY_LOCALISED("Security", String.class),
        JUMP_DIST("JumpDist", BigDecimal.class),
        FUEL_USED("FuelUsed", BigDecimal.class),
        FUEL_LEVEL("FuelLevel", BigDecimal.class),
        BOOST_USED("BoostUsed", Boolean.class),
        FACTION("Faction", String.class),
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
}
