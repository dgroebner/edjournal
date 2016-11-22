package de.dgroebner.edjson.db;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Properties.ENTRIES;
import de.dgroebner.edjson.db.dao.StarDao;
import de.dgroebner.edjson.db.model.DBStar;

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

}
