package de.dgroebner.edjson.model.data;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.model.GenericModel;
import de.dgroebner.edjson.model.JournalModelField;

/**
 * Modellklasse für den Elite Dangerous Journaleintrag 'MarketSell'
 * 
 * @author dgroebner
 */
public class MarketSell extends GenericModel<MarketSell.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarketSell.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'MarketSell'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        TIMESTAMP("timestamp", LocalDateTime.class),
        EVENT("event", String.class),
        TYPE("Type", String.class),
        COUNT("Count", Integer.class),
        SELL_PRICE("SellPrice", Integer.class),
        TOTAL_SALE("TotalSale", Integer.class),
        AVG_PRICE_PAID("AvgPricePaid", Integer.class),
        ILLEGAL_GOODS("IllegalGoods", Boolean.class),
        STOLE_GOODS("StolenGoods", Boolean.class),
        BLACK_MARKED("BlackMarket", Boolean.class);
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
    public MarketSell(final JSONObject json) {
        super(json, Arrays.asList(Fields.values()));
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }
}
