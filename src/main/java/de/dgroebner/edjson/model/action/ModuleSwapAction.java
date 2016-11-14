package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.ModuleSwap.Fields.FROM_ITEM_LOCALISED;
import static de.dgroebner.edjson.model.data.ModuleSwap.Fields.SHIP;
import static de.dgroebner.edjson.model.data.ModuleSwap.Fields.SHIP_ID;
import static de.dgroebner.edjson.model.data.ModuleSwap.Fields.TO_ITEM_LOCALISED;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Ship;
import de.dgroebner.edjson.db.model.DBShip;
import de.dgroebner.edjson.model.data.ModuleSwap;

/**
 * Action für das JournalModell {@link ModuleSwap}
 * 
 * @author dgroebner
 */
public class ModuleSwapAction extends AbstractAction<ModuleSwap> {

    @Override
    protected String buildJournalMessage(final DBI dbi, final ModuleSwap model) {
        final DBShip ship = new Ship(dbi).saveAndGetShip(model.getValueAsInt(SHIP_ID), model.getValueAsString(SHIP));
        final String callsign = StringUtils.defaultString(ship.getCallsign(), "unbenannt");

        return String.format("Position von Module %s und %s in Schiff %s (%s) getauscht",
                model.getValueAsString(FROM_ITEM_LOCALISED), model.getValueAsString(TO_ITEM_LOCALISED), callsign,
                ship.getType());
    }

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final ModuleSwap model) {
    }

}
