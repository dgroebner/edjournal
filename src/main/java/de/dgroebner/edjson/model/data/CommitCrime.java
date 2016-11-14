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
 * Modellklasse für den Elite Dangerous Journaleintrag 'CommitCrime'
 * 
 * @author dgroebner
 */
public class CommitCrime extends GenericModel<CommitCrime.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommitCrime.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'CommitCrime'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        TIMESTAMP("timestamp", LocalDateTime.class),
        EVENT("event", String.class),
        CRIME_TYPE("CrimeType", String.class),
        FACTION("Faction", String.class),
        VICTIM("Victim", String.class),
        FINE("Fine", Integer.class),
        BOUNTY("Bounty", Integer.class);
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
    public CommitCrime(final JSONObject json) {
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
        final StringBuilder message = new StringBuilder();
        message.append(String.format("Rechtsverstoß %s", getValueAsString(Fields.CRIME_TYPE)));

        final String victim = getValueAsString(Fields.VICTIM);
        if (StringUtils.isNotBlank(victim)) {
            message.append(String.format("gegen %s", victim));
        }
        
        message.append(String.format(" festgestellt von %s", getValueAsString(Fields.FACTION)));
        
        final int fine = getValueAsInt(Fields.FINE);
        if (fine > 0) {
            message.append(String.format(" Strafe in Höhe von %scr festgelegt", Integer.valueOf(fine)));
        }
         
        final int bounty = getValueAsInt(Fields.BOUNTY);
        if (bounty > 0) {
            message.append(String.format(" Kopfgeld in Höhe von %scr ausgesetzt", Integer.valueOf(bounty)));
        }
        
        return message.toString();
    }
}
