package de.dgroebner.edjson.db;

import java.math.BigDecimal;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.dao.PlanetMaterialDao;

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

}
