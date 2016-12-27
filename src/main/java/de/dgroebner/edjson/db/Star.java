package de.dgroebner.edjson.db;

import java.util.List;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Properties.ENTRIES;
import de.dgroebner.edjson.db.dao.StarDao;
import de.dgroebner.edjson.db.dao.VStarDao;
import de.dgroebner.edjson.db.model.DBStar;
import de.dgroebner.edjson.db.model.VStar;

/**
 * Methoden für die Datenbanktabelle 'star' zur Speicherung von Sternen
 * 
 * @author dgroebner
 */
public class Star extends AbstractDBTable {

    /**
     * Constructor.
     *
     * @param dbi {@link DBI}
     */
    public Star(final DBI dbi) {
        super(dbi);
    }

    /**
     * Speichert einen neuen Stern
     * 
     * @param journalId int
     * @param forSave {@link DBStar}
     * @return int
     */
    public int saveNew(final int journalId, final DBStar forSave) {
        final int currentStarSystem = new Properties(getDbi()).getIntValueForEntry(ENTRIES.CURRENT_STAR_SYSTEM);
        final StarDao dao = getDbi().open(StarDao.class);
        try {
            return dao.insert(journalId, currentStarSystem, forSave.getName(), forSave.getType(),
                    forSave.getDistanceFromArrivalLS(), forSave.getStellarMass(), forSave.getRadius(),
                    forSave.getAbsoluteMagnitude(), forSave.getAgeMY(), forSave.getSurfaceTemperature(),
                    forSave.getSemiMajorAxis(), forSave.getEccentricity(), forSave.getOrbitalInclination(),
                    forSave.getPeriapsis(), forSave.getOrbitalPeriod(), forSave.getRotationPeriod());
        } finally {
            dao.close();
        }
    }

    /**
     * Gibt die Anzahl der Sterne zurück
     * 
     * @return int
     */
    public int count() {
        final StarDao dao = getDbi().open(StarDao.class);
        try {
            return dao.count();
        } finally {
            dao.close();
        }
    }

    /**
     * Gibt eine Liste der Sterne zurück
     * 
     * @return {@link List} von {@link VStar}
     */
    public List<VStar> list() {
        final VStarDao dao = getDbi().open(VStarDao.class);
        try {
            return dao.list();
        } finally {
            dao.close();
        }
    }

    /**
     * Gibt eine Liste der Sterne für das übergebene Sternensystem zurück
     * 
     * @param starsystemId id des Starsystem
     * @return {@link List} von {@link VStar}
     */
    public List<VStar> listByStarsystemId(final Integer starsystemId) {
        final VStarDao dao = getDbi().open(VStarDao.class);
        try {
            return dao.list(starsystemId);
        } finally {
            dao.close();
        }
    }

}
