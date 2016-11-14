package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.Resurrect.Fields.COST;
import static de.dgroebner.edjson.model.data.Resurrect.Fields.OPTION;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Finanzdata;
import de.dgroebner.edjson.db.Finanzdata.CATEGORY;
import de.dgroebner.edjson.db.Properties;
import de.dgroebner.edjson.db.Properties.ENTRIES;
import de.dgroebner.edjson.db.Ship;
import de.dgroebner.edjson.db.model.DBShip;
import de.dgroebner.edjson.model.data.Resurrect;
import de.dgroebner.edjson.model.data.Resurrect.OPTIONS;

/**
 * Action für das JournalModell {@link Resurrect}
 * 
 * @author dgroebner
 */
public class ResurrectAction extends AbstractAction<Resurrect> {

    @Override
    protected String buildJournalMessage(final DBI dbi, final Resurrect model) {
        final int shipId = new Properties(dbi).getIntValueForEntry(ENTRIES.CURRENT_SHIP);
        final DBShip ship = new Ship(dbi).getById(shipId);
        final String callsign = StringUtils.defaultString(ship.getCallsign(), "unbenannt");

        final StringBuilder message = new StringBuilder();
        message.append(String.format("Schiff %s (%s)", callsign, ship.getType()));
        
        final OPTIONS option = OPTIONS.forText(model.getValueAsString(OPTION));
        if (option == OPTIONS.REBUY) {
            message.append(" über Versicherung wiederhergestellt");
        }
        
        return message.toString();
    }

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final Resurrect model) {
        final int cost = model.getValueAsInt(COST);
        if (cost > 0) {
            new Finanzdata(dbi).save(journalId, model.getTimestamp(), cost * -1, CATEGORY.INSURANCE,
                    "Versicherungskosten für Schiffswiederherstellung");
        }
    }

}
