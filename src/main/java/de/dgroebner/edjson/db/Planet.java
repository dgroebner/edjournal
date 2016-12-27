package de.dgroebner.edjson.db;

import java.util.List;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Properties.ENTRIES;
import de.dgroebner.edjson.db.dao.PlanetDao;
import de.dgroebner.edjson.db.dao.VPlanetDao;
import de.dgroebner.edjson.db.model.DBPlanet;
import de.dgroebner.edjson.db.model.VPlanet;

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

    /**
     * Gibt die Anzahl der Planeten zurück
     * 
     * @return int
     */
    public int count() {
        final PlanetDao dao = getDbi().open(PlanetDao.class);
        try {
            return dao.count();
        } finally {
            dao.close();
        }
    }

    /**
     * Gibt eine Liste der Planeten zurück
     * 
     * @return int
     */
    public List<VPlanet> list() {
        final VPlanetDao dao = getDbi().open(VPlanetDao.class);
        try {
            return dao.list();
        } finally {
            dao.close();
        }
    }

    /**
     * Gibt eine Liste der Planeten für das übergebene Sternensystem zurück
     * 
     * @param starsystemId id des Starsystem
     * @return {@link List} von {@link VPlanet}
     */
    public List<VPlanet> listByStarsystemId(final Integer starsystemId) {
        final VPlanetDao dao = getDbi().open(VPlanetDao.class);
        try {
            return dao.list(starsystemId);
        } finally {
            dao.close();
        }
    }

}
