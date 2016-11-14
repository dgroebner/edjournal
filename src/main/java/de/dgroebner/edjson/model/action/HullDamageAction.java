package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.HullDamage.Fields.HEALTH;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Properties;
import de.dgroebner.edjson.db.Properties.ENTRIES;
import de.dgroebner.edjson.db.Ship;
import de.dgroebner.edjson.db.model.DBShip;
import de.dgroebner.edjson.model.data.HullDamage;

/**
 * Action für das JournalModell {@link HullDamage}
 * 
 * @author dgroebner
 */
public class HullDamageAction extends AbstractAction<HullDamage> {

    @Override
    protected String buildJournalMessage(final DBI dbi, final HullDamage model) {
        final int shipId = new Properties(dbi).getIntValueForEntry(ENTRIES.CURRENT_SHIP);
        final DBShip ship = new Ship(dbi).getById(shipId);
        final String callsign = StringUtils.defaultString(ship.getCallsign(), "unbenannt");

        return String.format("Hüllenschaden an Schiff %s (%s). Verbleibende Hülle %s%%", callsign, ship.getType(),
                model.getValueAsBigDecimal(HEALTH).multiply(new BigDecimal(100)));
    }

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final HullDamage model) {
    }

}
