package de.dgroebner.edjson.model.data;

import java.math.BigDecimal;
import java.util.Arrays;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.model.GenericModel;
import de.dgroebner.edjson.model.JournalModelField;

/**
 * Modellklasse für den Elite Dangerous Journaleintrag 'RewardPerFaction' als Bestandtail des Typs {@link Bounty}
 * 
 * @author dgroebner
 */
public class RewardPerFaction extends GenericModel<RewardPerFaction.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RewardPerFaction.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'RewardPerFaction'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        FACTION("Faction", String.class),
        REWARD("Reward", BigDecimal.class);
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
    public RewardPerFaction(final JSONObject json) {
        super(json, Arrays.asList(Fields.values()));
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }
}
