package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.LoadGame.Fields.COMMANDER;
import static de.dgroebner.edjson.model.data.LoadGame.Fields.CREDITS;
import static de.dgroebner.edjson.model.data.LoadGame.Fields.SHIP;
import static de.dgroebner.edjson.model.data.LoadGame.Fields.SHIP_ID;
import static de.dgroebner.edjson.model.data.LoadGame.Fields.TIMESTAMP;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Finanzdata;
import de.dgroebner.edjson.db.Finanzdata.CATEGORY;
import de.dgroebner.edjson.db.Properties;
import de.dgroebner.edjson.db.Properties.ENTRIES;
import de.dgroebner.edjson.db.Ship;
import de.dgroebner.edjson.db.model.DBShip;
import de.dgroebner.edjson.model.data.LoadGame;

/**
 * Action für das JournalModell {@link LoadGame}
 * 
 * @author dgroebner
 */
public class LoadGameAction extends AbstractAction<LoadGame> {

    @Override
    protected String buildJournalMessage(final DBI dbi, final LoadGame model) {
        final Ship ship = new Ship(dbi);
        final DBShip dbShip = ship.writeOrGetShip(model.getValueAsInt(SHIP_ID), model.getValueAsString(SHIP));
        final Properties property = new Properties(dbi);
        property.save(ENTRIES.CURRENT_SHIP, dbShip.getId());
        final String callsign = StringUtils.defaultString(dbShip.getCallsign(), "unbenannt");
        return String.format("Spiel geladen für Commander %s auf Schiff %s vom Typ %s",
                model.getValueAsString(COMMANDER), callsign, model.getValueAsString(SHIP));
    }

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final LoadGame model) {
        final Finanzdata finanzdata = new Finanzdata(dbi);
        final int currentAmount = finanzdata.getSaldo();
        final int credits = model.getValueAsInt(CREDITS);
        if (currentAmount != model.getValueAsInt(CREDITS)) {
            finanzdata.save(journalId, model.getValueAsLocalDateTime(TIMESTAMP), credits - currentAmount,
                    CATEGORY.MISSING_SALES, "Angleichung Umsätze an E.D-Daten");
        }
    }

}
