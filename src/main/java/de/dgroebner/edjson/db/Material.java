package de.dgroebner.edjson.db;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.dao.MaterialDao;
import de.dgroebner.edjson.db.model.DBMaterial;

/**
 * Methoden für die Datenbanktabelle 'material' zur Speicherung der Materialien
 * 
 * @author dgroebner
 */
public class Material extends AbstractDBTable {

    /**
     * Constructor.
     *
     * @param dbi {@link DBI}
     */
    public Material(final DBI dbi) {
        super(dbi);
    }

    /**
     * Schreibt das übergebene Material in die Datenbank sofern noch nicht vorhanden. Anderenfalls wird der Stock um den
     * übergebenen count erhöht.
     * 
     * @param edName {@link String}
     * @param category {@link String}
     * @param count int
     */
    public void save(final String edName, final String category, final int count) {
        final MaterialDao dao = getDbi().open(MaterialDao.class);
        try {
            final DBMaterial storedValue = dao.findValueByEdName(edName);
            if (storedValue != null) {
                dao.updateStockById(storedValue.getStock() + count, storedValue.getId());
            } else {
                dao.insert(null, null, null, edName, category, count);
            }
        } finally {
            dao.close();
        }
    }

    /**
     * Liefert den Materialeintrag für den übergebenen Elite-Dangerous Namen zurück. Liefert <code>null</code> wenn noch
     * kein Eintrag vorhandne ist.
     * 
     * @param edName {@link String}
     * @return {@link DBMaterial}
     */
    public DBMaterial getMaterialByEdName(final String edName) {
        final MaterialDao dao = getDbi().open(MaterialDao.class);
        try {
            return dao.findValueByEdName(edName);
        } finally {
            dao.close();
        }
    }
}
