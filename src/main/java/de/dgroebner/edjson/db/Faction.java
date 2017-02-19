package de.dgroebner.edjson.db;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Properties.ENTRIES;
import de.dgroebner.edjson.db.dao.FactionDao;
import de.dgroebner.edjson.db.model.DBFaction;

/**
 * Methoden für die Datenbanktabelle 'fraction' zur Speicherung der Fraktionen
 * 
 * @author dgroebner
 */
public class Faction extends AbstractDBTable {

    public static final String UNDEFINED = "<unbestimmt>";

    public static final DBFaction UNDEFINED_FACTION = DBFaction.builder().name(UNDEFINED).build();

    /**
     * Constructor.
     *
     * @param dbi {@link DBI}
     */
    public Faction(final DBI dbi) {
        super(dbi);
    }

    /**
     * Schreibt eine neuen Fraktion in die Datenbank.
     * 
     * @param journalId int
     * @param name {@link String}
     * @param state {@link String}
     * @return {@link DBFaction}
     */
    public DBFaction writeOrGetFraction(final int journalId, final String name, final String state) {
        final FactionDao dao = getDbi().open(FactionDao.class);
        final String nameForDB = StringUtils.defaultIfBlank(name, UNDEFINED);
        try {
            final DBFaction saved = dao.findByName(nameForDB);
            if (saved != null) {
                dao.updateState(state, saved.getId());
            } else {
                dao.insert(journalId, nameForDB, state, null, null);
            }
            return dao.findByName(nameForDB);
        } finally {
            dao.close();
        }
    }

    /**
     * Gibt die Faction für die übergebene id zurück
     * 
     * @param id int
     * @return {@link DBFaction}
     */
    public DBFaction getById(final int id) {
        final FactionDao dao = getDbi().open(FactionDao.class);
        try {
            return dao.findById(id);
        } finally {
            dao.close();
        }
    }

    /**
     * Gibt die Fraktion mit dem übergebenen Namen zurück, erzeugt eine neue, sofern noch nicht vorhanden.
     * 
     * @param journalId int
     * @param name {@link String}
     * @return {@link DBFaction} oder null
     */
    public DBFaction writeOrGetFraction(final int journalId, final String name) {
        final FactionDao dao = getDbi().open(FactionDao.class);
        try {
            final DBFaction saved = dao.findByName(name);
            if (saved != null) {
                return saved;
            }
            final int id = dao.insert(journalId, name, null, null, null);
            return dao.findById(id);
        } finally {
            dao.close();
        }
    }

    /**
     * Gibt die aktuelle lokale Fraktion zurück.
     * 
     * @return {@link DBFaction} oder null
     */
    public DBFaction getCurrentLocalFaction() {
        final int currentFaction = new Properties(getDbi()).getIntValueForEntry(ENTRIES.CURRENT_LOCAL_FACTION);
        return currentFaction == Integer.MIN_VALUE ? UNDEFINED_FACTION : getById(currentFaction);
    }

}
