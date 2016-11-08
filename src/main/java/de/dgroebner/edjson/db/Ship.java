package de.dgroebner.edjson.db;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.dao.ShipDao;
import de.dgroebner.edjson.db.model.DBShip;

/**
 * Methoden für die Datenbanktabelle 'ship' zur Speicherung der Schiffe
 * 
 * @author dgroebner
 */
public class Ship extends AbstractDBTable {

    /**
     * Constructor.
     *
     * @param dbi {@link DBI}
     */
    public Ship(final DBI dbi) {
        super(dbi);
    }

    /**
     * Schreibt ein neues Schiff in die Datenbank, sofern noch nicht vorhanden und gibt diese zurück. Die ID des
     * Datensatzes kann anschließend über die Methode {@link Ship#getDatabaseId()} abgefragt werden.
     * 
     * @param edId int
     * @param type {@link String}
     * @return {@link DBShip}
     */
    public DBShip writeOrGetShip(final int edId, final String type) {
        final ShipDao dao = getDbi().open(ShipDao.class);
        try {
            final DBShip selected = dao.findByEdId(edId);
            if (selected != null) {
                setId(selected.getId());
            } else {
                setId(dao.insert(edId, type, null, null));
            }
            return dao.findByEdId(edId);
        } finally {
            dao.close();
        }
    }

}
