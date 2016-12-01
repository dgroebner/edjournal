package de.dgroebner.edjson.db;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Properties.ENTRIES;
import de.dgroebner.edjson.db.dao.CombatlogDao;
import de.dgroebner.edjson.db.dao.VCombatlogDao;
import de.dgroebner.edjson.db.model.DBFaction;
import de.dgroebner.edjson.db.model.VCombatlog;

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
        INTERDICTED, ENEMY_KILLED, DIED;
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
        final Faction factionDao = new Faction(getDbi());
        final DBFaction faction = factionDao.writeOrGetFraction(journalId, StringUtils.isBlank(factionName)
                ? Faction.UNDEFINED : factionName);
        final CombatlogDao dao = getDbi().open(CombatlogDao.class);
        try {
            dao.insert(journalId, Integer.parseInt(currentShip), timestamp, action.name(), enemy, faction.getId(),
                    reward);
        } finally {
            dao.close();
        }
    }

    /**
     * Listet das komplette Combatlog auf
     * 
     * @return {@link List} von {@link VCombatlog}
     */
    public List<VCombatlog> list() {
        final VCombatlogDao dao = getDbi().open(VCombatlogDao.class);
        try {
            return dao.list();
        } finally {
            dao.close();
        }
    }

}
