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
 * Modellklasse für den Elite Dangerous Journaleintrag 'Materials'
 * 
 * @author dgroebner
 */
public class Ingredients extends GenericModel<Ingredients.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Ingredients.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'Materials'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        PRASEODYMIUM("praseodymium", Integer.class),
        
        /* Industrials */
        CHEMICAL_PROCESSORS("chemicalprocessors", Integer.class),

        /* Data */
        DISRUPTED_WAKEECHOES("disruptedwakeechoes", Integer.class),
        
        /* Elements */
        PHOSPHORUS("phosphorus", BigDecimal.class),
        NICKEL("nickel", BigDecimal.class),
        CHROMIUM("chromium", BigDecimal.class),
        MANGANESE("manganese", BigDecimal.class),
        GERMANIUM("germanium", BigDecimal.class),
        VANADIUM("vanadium", BigDecimal.class),
        CADMIUM("cadmium", BigDecimal.class),
        TELLURIUM("tellurium", BigDecimal.class),
        YTTRIUM("yttrium", BigDecimal.class),
        TUNGSTEN("tungsten", BigDecimal.class),
        POLONIUM("polonium", BigDecimal.class),
        TECHNETIUM("technetium", BigDecimal.class),
        MERCURY("mercury", BigDecimal.class),
        NIOBIUM("niobium", BigDecimal.class),
        ARSENIC("arsenic", BigDecimal.class),
        ZINC("zinc", BigDecimal.class),
        TIN("tin", BigDecimal.class),
        ANTIMONY("antimony", BigDecimal.class),
        RUTHETIUM("ruthenium", BigDecimal.class),
        ZIRCONIUM("zirconium", BigDecimal.class),
        SELENIUM("selenium", BigDecimal.class),
        MOLYBDENUM("molybdenum", BigDecimal.class);
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
    public Ingredients(final JSONObject json) {
        super(json, Arrays.asList(Fields.values()));
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    /**
     * Sind Materialen vorhanden
     * 
     * @return boolean
     */
    public boolean areMaterialsPresent() {
        return Arrays.asList(Fields.values()).stream().anyMatch(field -> getValueAsInt(field) != 0);
    }

    @Override
    public LocalDateTime getTimestamp() {
        return null;
    }

    @Override
    public String getEvent() {
        return null;
    }
}
