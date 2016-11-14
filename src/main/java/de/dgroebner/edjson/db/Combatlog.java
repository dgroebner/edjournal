package de.dgroebner.edjson.db;

import java.time.LocalDateTime;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Properties.ENTRIES;
import de.dgroebner.edjson.db.dao.CombatlogDao;
import de.dgroebner.edjson.db.model.DBFaction;

/**
 * Methoden für die Datenbanktabelle 'combatlog' zur Speicherung des Kampfprotokolls
 * 
 * @author dgroebner
 */
public class Combatlog extends AbstractDBTable {

    /**
     * Kampflogaktionen
     * 
     * @author dgroebner
     */
    public enum ACTION {
        INTERDICTED, FIGHT_WON;
    }

    /**
     * Constructor.
     *
     * @param dbi {@link DBI}
     */
    public Combatlog(final DBI dbi) {
        super(dbi);
    }

    /**
     * Schreibt ein neues Kampfprotokoll in die Datenbank.
     * 
     * @param journalId int
     * @param timestamp {@link LocalDateTime}
     * @param action {@link ACTION}
     * @param enemy {@link String}
     * @param factionName {@link String}
     * @param reward int
     */
    public void save(final int journalId, final LocalDateTime timestamp, final ACTION action, final String enemy,
            final String factionName, final int reward) {
        final String currentShip = new Properties(getDbi()).getValueForEntry(ENTRIES.CURRENT_SHIP);
        final DBFaction faction = new Faction(getDbi()).writeOrGetFraction(journalId, factionName);
        final CombatlogDao dao = getDbi().open(CombatlogDao.class);
        try {
            dao.insert(journalId, Integer.parseInt(currentShip), timestamp, action.name(), enemy, faction.getId(),
                    reward);
        } finally {
            dao.close();
        }
    }

}
