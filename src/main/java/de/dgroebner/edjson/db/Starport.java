package de.dgroebner.edjson.db;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.dao.StarportDao;
import de.dgroebner.edjson.db.dao.StarportVisitsDao;
import de.dgroebner.edjson.db.model.DBShip;
import de.dgroebner.edjson.db.model.DBStarport;

/**
 * Methoden für die Datenbanktabelle 'starport' zur Speicherung der Sternenhäfen
 * 
 * @author dgroebner
 */
public class Starport extends AbstractDBTable {

    /**
     * Constructor.
     *
     * @param dbi {@link DBI}
     */
    public Starport(final DBI dbi) {
        super(dbi);
    }

    /**
     * Speichert ein neuen Sternenhafen sofern noch nicht vorhanden und gibt das gespeicherte Datenbankobjekt zurück
     * 
     * @param journalId int
     * @param forSave {@link DBStarport}
     * @return {@link DBStarport}
     */
    public DBStarport writeOrGetStarport(final int journalId, final DBStarport forSave) {
        final StarportDao dao = getDbi().open(StarportDao.class);
        try {
            final DBStarport saved = dao.findByName(forSave.getName());
            if (saved != null) {
                dao.updateMetaData(forSave.getFactionId(), forSave.getSecurity(), forSave.getAllegiance(),
                        forSave.getGovernment(), forSave.getEconomy(), forSave.getName());
            } else {
                dao.insert(journalId, forSave.getStarsystemId(), forSave.getName(), forSave.getType(), null,
                        forSave.getFactionId(), forSave.getSecurity(), forSave.getAllegiance(),
                        forSave.getGovernment(), forSave.getEconomy());
            }
            final DBStarport newPort = dao.findByName(forSave.getName());
            saveVisit(journalId, newPort);
            return newPort;
        } finally {
            dao.close();
        }
    }

    /**
     * Speichert einen neuen Starport Besuch
     * 
     * @param journalId int
     * @param saved {@link DBStarport}
     */
    private void saveVisit(final int journalId, final DBStarport saved) {
        final DBShip ship = new Ship(getDbi()).getCurrentShip();
        final StarportVisitsDao dao = getDbi().open(StarportVisitsDao.class);
        try {
            dao.insert(journalId, saved.getId(), ship.getId());
        } finally {
            dao.close();
        }
    }

}
