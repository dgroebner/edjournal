package de.dgroebner.edjson.model.data;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.model.GenericModel;
import de.dgroebner.edjson.model.JournalModelField;

/**
 * Modellklasse für den Elite Dangerous Journaleintrag 'Bounty'
 * 
 * @author dgroebner
 */
public class Bounty extends GenericModel<Bounty.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Bounty.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'Bounty'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        TIMESTAMP("timestamp", LocalDateTime.class),
        EVENT("event", String.class),
        REWARDS("Rewards", JSONArray.class),
        TOTAL_REWARD("TotalReward", Integer.class),
        VICTIM_FACTION("VictimFaction", String.class),
        SHARED_WITH_OTHERS("SharedWithOthers", Boolean.class);
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
    public Bounty(final JSONObject json) {
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

    /**
     * Liefert ein Set der Einzelbelohnungen je Fraktion
     * 
     * @return {@link Set} of {@link RewardPerFaction}
     */
    public Set<RewardPerFaction> getRewardPerFaction() {
        final Set<RewardPerFaction> rewards = new HashSet<RewardPerFaction>();
        getValueAsJsonArray(Fields.REWARDS).forEach(reward -> rewards.add(new RewardPerFaction((JSONObject) reward)));
        return rewards;
    }
}
