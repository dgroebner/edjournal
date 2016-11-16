package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.RefuelAll.Fields.AMOUNT;
import static de.dgroebner.edjson.model.data.RefuelAll.Fields.COST;
import static de.dgroebner.edjson.model.data.RefuelAll.Fields.TIMESTAMP;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Financedata;
import de.dgroebner.edjson.db.Financedata.CATEGORY;
import de.dgroebner.edjson.db.Ship;
import de.dgroebner.edjson.db.model.DBShip;
import de.dgroebner.edjson.model.data.RefuelAll;

/**
 * Action für das JournalModell {@link RefuelAll}
 * 
 * @author dgroebner
 */
public class RefuellAllAction extends AbstractAction<RefuelAll> {

    @Override
    protected String buildJournalMessage(final DBI dbi, final RefuelAll model) {
        final DBShip ship = new Ship(dbi).getCurrentShip();
        final String callsign = StringUtils.defaultString(ship.getCallsign(), "unbenannt");

        return String.format("Schiff %s (%s) vollständig aufgetankt", callsign, ship.getType());
    }

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final RefuelAll model) {
        new Financedata(dbi).save(journalId, model.getValueAsLocalDateTime(TIMESTAMP), model.getValueAsInt(COST) * -1,
                CATEGORY.OPERATING_COSTS,
                String.format("Treibstoff gekauft. Menge: %st", model.getValueAsBigDecimal(AMOUNT)));
    }

}
