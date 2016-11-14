package de.dgroebner.edjson.model.data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.model.GenericModel;
import de.dgroebner.edjson.model.JournalModelField;

/**
 * Modellklasse für den Elite Dangerous Journaleintrag 'Materials' aus dem Objekt {@link Scan}
 * 
 * @author dgroebner
 */
public class ScannedMaterials extends GenericModel<ScannedMaterials.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScannedMaterials.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'Materials' im Object 'Scan'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        SULPHUR("sulphur", BigDecimal.class),
        CARBON("carbon", BigDecimal.class),
        IRON("iron", BigDecimal.class),
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
    public ScannedMaterials(final JSONObject json) {
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
        return Arrays.asList(Fields.values()).stream()
                .anyMatch(field -> BigDecimal.ZERO.compareTo(getValueAsBigDecimal(field)) != 0);
    }

    /**
     * Gibt eine Liste der vorhandenen Materialen zurück
     * 
     * @return boolean
     */
    public List<Fields> getPresentMaterials() {
        return Arrays.asList(Fields.values()).stream()
                .filter(field -> BigDecimal.ZERO.compareTo(getValueAsBigDecimal(field)) != 0)
                .collect(Collectors.toList());
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
