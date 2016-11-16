package de.dgroebner.edjson.db;

import java.util.List;
import java.util.stream.Collectors;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Properties.ENTRIES;
import de.dgroebner.edjson.db.dao.ShipDao;
import de.dgroebner.edjson.db.dao.VShipSummaryDao;
import de.dgroebner.edjson.db.model.DBShip;
import de.dgroebner.edjson.db.model.VShipSummary;

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
     * Schreibt ein neues Schiff in die Datenbank, sofern noch nicht vorhanden und gibt diese zurück.
     * 
     * @param edId int
     * @param type {@link String}
     * @return {@link DBShip}
     */
    public DBShip saveAndGetShip(final int edId, final String type) {
        final ShipDao dao = getDbi().open(ShipDao.class);
        try {
            final DBShip selected = dao.findByEdId(edId);
            if (selected == null) {
                dao.insert(edId, type, null, null);
            }
            return dao.findByEdId(edId);
        } finally {
            dao.close();
        }
    }

    /**
     * Gibt das Schiff für die aktuelle ID zurück
     * 
     * @param id int
     * @return {@link DBShip}
     */
    public DBShip getById(final int id) {
        final ShipDao dao = getDbi().open(ShipDao.class);
        try {
            return dao.findById(id);
        } finally {
            dao.close();
        }
    }

    /**
     * Löscht das Schiff mit der übergebenen Elite Dangerous-ID aus dem Bestand
     * 
     * @param edId int
     */
    public void delete(final int edId) {
        final ShipDao dao = getDbi().open(ShipDao.class);
        try {
            final DBShip selected = dao.findByEdId(edId);
            if (selected != null) {
                dao.deleteById(selected.getId());
            }
        } finally {
            dao.close();
        }
    }

    /**
     * Gibt das aktuelle Schiff zurück
     * 
     * @return {@link DBShip}
     */
    public DBShip getCurrentShip() {
        final int shipId = new Properties(getDbi()).getIntValueForEntry(ENTRIES.CURRENT_SHIP);
        return getById(shipId);
    }

    /**
     * Gibt das Schiff für die aktuelle ID zurück
     * 
     * @return {@link List} of {@link VShipSummary}
     */
    public List<VShipSummary> listShipSummary() {
        final VShipSummaryDao dao = getDbi().open(VShipSummaryDao.class);
        try {
            return dao.list().stream()
                    .sorted((VShipSummary o1, VShipSummary o2) -> o1.getCallsign().compareTo(o2.getCallsign()))
                    .collect(Collectors.toList());
        } finally {
            dao.close();
        }
    }

}
