package de.dgroebner.edjson.db;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Properties.ENTRIES;
import de.dgroebner.edjson.db.dao.PlanetDao;
import de.dgroebner.edjson.db.model.DBPlanet;

/**
 * Methoden für die Datenbanktabelle 'planet' zur Speicherung von Planeten
 * 
 * @author dgroebner
 */
public class Planet extends AbstractDBTable {

    /**
     * Constructor.
     *
     * @param dbi {@link DBI}
     */
    public Planet(final DBI dbi) {
        super(dbi);
    }

    /**
     * Speichert einen neuen Planeten
     * 
     * @param journalId int
     * @param forSave {@link DBPlanet}
     * @return int
     */
    public int saveNew(final int journalId, final DBPlanet forSave) {
        final int currentStarSystem = new Properties(getDbi()).getIntValueForEntry(ENTRIES.CURRENT_STAR_SYSTEM);
        final PlanetDao dao = getDbi().open(PlanetDao.class);
        try {
            return dao.insert(journalId, currentStarSystem, forSave.getName(), forSave.getType(),
                    forSave.getDistanceFromArrivalLS(), forSave.isTidalLock(), forSave.getTerraformState(),
                    forSave.getAtmosphere(), forSave.getVolcanism(), forSave.getMassEM(), forSave.getRadius(),
                    forSave.getSurfaceGravity(), forSave.getSurfaceTemperature(), forSave.getSurfacePressure(),
                    forSave.isLandable(), forSave.getSemiMajorAxis(), forSave.getEccentricity(),
                    forSave.getOrbitalInclination(), forSave.getPeriapsis(), forSave.getOrbitalPeriod(),
                    forSave.getRotationPeriod());
        } finally {
            dao.close();
        }
    }

    /**
     * Gibt die id des Planeten für den übergebenen Namen zurück
     * 
     * @param name {@link String}
     * @return int
     */
    public int getIdForName(final String name) {
        final PlanetDao dao = getDbi().open(PlanetDao.class);
        try {
            return dao.getIdForName(name);
        } finally {
            dao.close();
        }
    }

}
