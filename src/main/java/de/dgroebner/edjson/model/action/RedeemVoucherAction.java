package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.RedeemVoucher.Fields.AMOUNT;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Faction;
import de.dgroebner.edjson.db.Finanzdata;
import de.dgroebner.edjson.db.Finanzdata.CATEGORY;
import de.dgroebner.edjson.model.data.PayFines;
import de.dgroebner.edjson.model.data.RedeemVoucher;

/**
 * Action für das JournalModell {@link PayFines}
 * 
 * @author dgroebner
 */
public class RedeemVoucherAction extends AbstractAction<RedeemVoucher> {

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final RedeemVoucher model) {
        new Finanzdata(dbi).save(journalId, model.getTimestamp(), model.getValueAsInt(AMOUNT), CATEGORY.BOUNTIES,
                "Kopfgelder eingelöst.", Faction.UNDEFINED, null);
    }

}
