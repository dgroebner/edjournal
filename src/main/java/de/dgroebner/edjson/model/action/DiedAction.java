package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.Died.Fields.KILLER_NAME;
import static de.dgroebner.edjson.model.data.Died.Fields.KILLER_RANK;
import static de.dgroebner.edjson.model.data.Died.Fields.KILLER_SHIP;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Properties;
import de.dgroebner.edjson.db.Properties.ENTRIES;
import de.dgroebner.edjson.db.Ship;
import de.dgroebner.edjson.db.model.DBShip;
import de.dgroebner.edjson.model.data.Died;
import de.dgroebner.edjson.model.data.HullDamage;
import de.dgroebner.edjson.model.data.Killer;

/**
 * Action für das JournalModell {@link HullDamage}
 * 
 * @author dgroebner
 */
public class DiedAction extends AbstractAction<Died> {

    @Override
    protected String buildJournalMessage(final DBI dbi, final Died model) {
        final int shipId = new Properties(dbi).getIntValueForEntry(ENTRIES.CURRENT_SHIP);
        final DBShip ship = new Ship(dbi).getById(shipId);
        final String callsign = StringUtils.defaultString(ship.getCallsign(), "unbenannt");
        
        final StringBuilder message = new StringBuilder();
        message.append(String.format("Schiff %s (%s) wurde zerstört", callsign, ship.getType()));
        
        final String singleKiller = model.getValueAsString(KILLER_NAME);
        if (StringUtils.isNotBlank(singleKiller)) {
            message.append(String.format(" von %s (%s) auf Schiffstyp %s", model.getValueAsString(KILLER_NAME),
                    model.getValueAsString(KILLER_RANK), model.getValueAsString(KILLER_SHIP)));
        }

        if (!model.getKillers().isEmpty()) {
            message.append(" von: ");
            model.getKillers().forEach(
                    killer -> message.append(String.format(" %s (%s) auf Schiffstyp %s,",
                            killer.getValueAsString(Killer.Fields.NAME), killer.getValueAsString(Killer.Fields.RANK),
                            killer.getValueAsString(Killer.Fields.SHIP))));
        }

        return message.toString();
    }

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final Died model) {
    }

}
