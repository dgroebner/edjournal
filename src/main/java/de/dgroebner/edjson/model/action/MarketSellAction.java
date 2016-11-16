package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.MarketSell.Fields.AVG_PRICE_PAID;
import static de.dgroebner.edjson.model.data.MarketSell.Fields.BLACK_MARKED;
import static de.dgroebner.edjson.model.data.MarketSell.Fields.COUNT;
import static de.dgroebner.edjson.model.data.MarketSell.Fields.SELL_PRICE;
import static de.dgroebner.edjson.model.data.MarketSell.Fields.TOTAL_SALE;
import static de.dgroebner.edjson.model.data.MarketSell.Fields.TYPE;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Financedata;
import de.dgroebner.edjson.db.Financedata.CATEGORY;
import de.dgroebner.edjson.model.data.MarketSell;

/**
 * Action für das JournalModell {@link MarketSell}
 * 
 * @author dgroebner
 */
public class MarketSellAction extends AbstractAction<MarketSell> {

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final MarketSell model) {
        final CATEGORY category = model.getValueAsBoolean(BLACK_MARKED) ? CATEGORY.BLACKMARKET : CATEGORY.MARKET;
        final int priceSold = model.getValueAsInt(SELL_PRICE);
        final int pricePaid = model.getValueAsInt(AVG_PRICE_PAID);

        final StringBuilder remark = new StringBuilder();
        remark.append(String.format("Verkauf von %st %s für %scr pro Tonne. ",
                Integer.valueOf(model.getValueAsInt(COUNT)), model.getValueAsString(TYPE), Integer.valueOf(priceSold)));

        final int profit = priceSold - pricePaid;
        remark.append((profit < 0) ? "Verlust" : "Gewinn");
        remark.append(" je Tonne ").append(Integer.toString(profit)).append("cr");
        
        new Financedata(dbi).save(journalId, model.getTimestamp(), model.getValueAsInt(TOTAL_SALE), category,
                remark.toString());
    }

}
