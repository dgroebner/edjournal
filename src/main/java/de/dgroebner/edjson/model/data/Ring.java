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
 * Modellklasse für den Elite Dangerous Journaleintrag 'Ring'
 * 
 * @author dgroebner
 */
public class Ring extends GenericModel<Ring.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Ring.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'Ring'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        NAME("Name", String.class),
        RING_CLASS("RingClass", String.class),
        MASS_MT("MassMT", BigDecimal.class),
        INNER_RAD("InnerRad", BigDecimal.class),
        OUTER_RAD("OuterRad", BigDecimal.class);
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
    public Ring(final JSONObject json) {
        super(json, Arrays.asList(Fields.values()));
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
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
