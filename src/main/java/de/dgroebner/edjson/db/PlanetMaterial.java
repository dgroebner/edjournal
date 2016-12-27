package de.dgroebner.edjson.db;

import java.math.BigDecimal;
import java.util.List;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.dao.PlanetMaterialDao;
import de.dgroebner.edjson.db.dao.VMaterialSummaryDao;
import de.dgroebner.edjson.db.model.VMaterialSummary;

/**
 * Methoden für die Datenbanktabelle 'planet_material' zur Speicherung der Materialien je Planet
 * 
 * @author dgroebner
 */
public class PlanetMaterial extends AbstractDBTable {

    /**
     * Constructor.
     *
     * @param dbi {@link DBI}
     */
    public PlanetMaterial(final DBI dbi) {
        super(dbi);
    }

    /**
     * Schreibt für eine neue Planet-Materialverknüpfung in die Datenbank.
     * 
     * @param journalId int
     * @param planetId int
     * @param materialName {@link String}
     * @param amount {@link BigDecimal}
     */
    public void save(final int journalId, int planetId, final String materialName, final BigDecimal amount) {
        final int materialId = new Material(getDbi()).getMaterialByEdName(materialName).getId();
        final PlanetMaterialDao dao = getDbi().open(PlanetMaterialDao.class);
        try {
            dao.insert(journalId, planetId, materialId, amount);
        } finally {
            dao.close();
        }
    }

    /**
     * Gibt die Anzahl der Planeten zurück, die Materialzuordnungen besitzen
     * 
     * @return int
     */
    public int countByPlanet() {
        final PlanetMaterialDao dao = getDbi().open(PlanetMaterialDao.class);
        try {
            return dao.countByPlanet();
        } finally {
            dao.close();
        }
    }

    /**
     * Gibt eine Liste Maximalvorkommen der Materialien zurück
     * 
     * @return {@link List} von {@link VMaterialSummary}
     */
    public List<VMaterialSummary> listMaterialSummary() {
        final VMaterialSummaryDao dao = getDbi().open(VMaterialSummaryDao.class);
        try {
            return dao.listSummary();
        } finally {
            dao.close();
        }
    }

    /**
     * Gibt eine Gesamtliste der Materialvorkommen zurück
     * 
     * @return {@link List} von {@link VMaterialSummary}
     */
    public List<VMaterialSummary> listMaterialComplete() {
        final VMaterialSummaryDao dao = getDbi().open(VMaterialSummaryDao.class);
        try {
            return dao.list();
        } finally {
            dao.close();
        }
    }

    /**
     * Gibt eine Gesamtliste der Materialvorkommen für den übergebenen Planeten zurück
     * 
     * @param planet {@link String} planet
     * @return {@link List} von {@link VMaterialSummary}
     */
    public List<VMaterialSummary> listMaterialByPlanet(final String planet) {
        final VMaterialSummaryDao dao = getDbi().open(VMaterialSummaryDao.class);
        try {
            return dao.list(planet);
        } finally {
            dao.close();
        }
    }

}
