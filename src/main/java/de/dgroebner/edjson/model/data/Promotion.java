package de.dgroebner.edjson.model.data;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.model.GenericModel;
import de.dgroebner.edjson.model.JournalModelField;

/**
 * Modellklasse für den Elite Dangerous Journaleintrag 'Rank'
 * 
 * @author dgroebner
 */
public class Promotion extends GenericModel<Promotion.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Promotion.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'Rank'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        TIMESTAMP("timestamp", LocalDateTime.class),
        EVENT("event", String.class),
        COMBAT("Combat", Integer.class),
        TRADE("Trade", Integer.class),
        EXPLORE("Explore", Integer.class),
        CQC("CQC", Integer.class);
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
    public Promotion(final JSONObject json) {
        super(json, Arrays.asList(Fields.values()));
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    /**
     * Liefet den Kampfrang zurück
     * 
     * @return {@link RankCombat}
     */
    public RankCombat getCombatRank() {
        return RankCombat.forCode(getValueAsInt(Fields.COMBAT));
    }

    /**
     * Liefet den Handelsrang zurück
     * 
     * @return {@link RankTrade}
     */
    public RankTrade getTradeRank() {
        return RankTrade.forCode(getValueAsInt(Fields.TRADE));
    }

    /**
     * Liefet den Explorersrang zurück
     * 
     * @return {@link RankExploration}
     */
    public RankExploration getExplorationRank() {
        return RankExploration.forCode(getValueAsInt(Fields.EXPLORE));
    }

    /**
     * Liefet den CQC-Rank zurück
     * 
     * @return {@link RankCQC}
     */
    public RankCQC getCQCRank() {
        return RankCQC.forCode(getValueAsInt(Fields.CQC));
    }

    /**
     * Gibt das Feldzurück, dessen Wert geändert wurde
     * 
     * @return {@link JournalModelField}
     */
    public JournalModelField getChangedField() {
        /* @formatter:off */
        return Arrays.asList(Fields.values()).stream()
                .filter(value -> !value.equals(Fields.EVENT))
                .filter(value -> !value.equals(Fields.TIMESTAMP))
                .filter(value -> getDataObject().has(value.getCode()))
                .findFirst().get();
        /* @formatter:off */
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).append("CombatRank", getCombatRank().getName())
                .append("TradeRank", getTradeRank().getName())
                .append("ExplorationRank", getExplorationRank().getName())
                .append("CQC-Rank", getCQCRank().getName()).toString();
    }

}
