package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.MarketBuy.Fields.BUY_PRICE;
import static de.dgroebner.edjson.model.data.MarketBuy.Fields.COUNT;
import static de.dgroebner.edjson.model.data.MarketBuy.Fields.TOTAL_COST;
import static de.dgroebner.edjson.model.data.MarketBuy.Fields.TYPE;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Finanzdata;
import de.dgroebner.edjson.db.Finanzdata.CATEGORY;
import de.dgroebner.edjson.model.data.MarketBuy;

/**
 * Action für das JournalModell {@link MarketBuy}
 * 
 * @author dgroebner
 */
public class MarketBuyAction extends AbstractAction<MarketBuy> {

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final MarketBuy model) {
        final String remark = String.format("Einkauf von %st %s zum Preis von %s je Tonne.",
                Integer.valueOf(model.getValueAsInt(COUNT)), model.getValueAsString(TYPE),
                Integer.valueOf(model.getValueAsInt(BUY_PRICE)));
        new Finanzdata(dbi).save(journalId, model.getTimestamp(), model.getValueAsInt(TOTAL_COST) * -1,
                CATEGORY.MARKET, remark);
    }

}
