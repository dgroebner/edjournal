package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.FetchRemoteModule.Fields.STORED_ITEM_LOCALISED;
import static de.dgroebner.edjson.model.data.FetchRemoteModule.Fields.TIMESTAMP;
import static de.dgroebner.edjson.model.data.FetchRemoteModule.Fields.TRANSFER_COST;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Financedata;
import de.dgroebner.edjson.db.Financedata.CATEGORY;
import de.dgroebner.edjson.db.Ship;
import de.dgroebner.edjson.db.model.DBShip;
import de.dgroebner.edjson.model.data.FetchRemoteModule;

/**
 * Action für das JournalModell {@link FetchRemoteModule}
 * 
 * @author dgroebner
 */
public class FetchRemoteModuleAction extends AbstractAction<FetchRemoteModule> {

    @Override
    protected String buildJournalMessage(final DBI dbi, final FetchRemoteModule model) {
        final DBShip ship = new Ship(dbi).getCurrentShip();
        final String callsign = StringUtils.defaultString(ship.getCallsign(), "unbenannt");

        return String.format("Transfer des Modul %s für Shiff %s (%s) angefordert",
                model.getValueAsString(STORED_ITEM_LOCALISED), callsign, ship.getType());
    }

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final FetchRemoteModule model) {
        new Financedata(dbi).save(journalId, model.getValueAsLocalDateTime(TIMESTAMP),
                model.getValueAsInt(TRANSFER_COST) * -1, CATEGORY.MODUL_STORAGE, "Modultransfer angefordert.");
    }

}
