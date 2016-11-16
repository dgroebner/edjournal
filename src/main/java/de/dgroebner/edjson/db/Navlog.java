package de.dgroebner.edjson.db;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Properties.ENTRIES;
import de.dgroebner.edjson.db.dao.NavlogDao;
import de.dgroebner.edjson.db.dao.VNavlogDao;
import de.dgroebner.edjson.db.model.VNavlog;

/**
 * Methoden für die Datenbanktabelle 'navlog' zur Speicherung des Navigationsprotokolls
 * 
 * @author dgroebner
 */
public class Navlog extends AbstractDBTable {

    /**
     * Constructor.
     *
     * @param dbi {@link DBI}
     */
    public Navlog(final DBI dbi) {
        super(dbi);
    }

    /**
     * Schreibt ein neues Navigationsprotokoll in die Datenbank.
     * 
     * @param journalId int
     * @param timestamp {@link LocalDateTime}
     * @param toSystemId int
     * @param distance {@link BigDecimal}
     * @param fuelUsed {@link BigDecimal}
     */
    public void save(final int journalId, final LocalDateTime timestamp, final int toSystemId,
            final BigDecimal distance, final BigDecimal fuelUsed) {
        final int currentShip = new Properties(getDbi()).getIntValueForEntry(ENTRIES.CURRENT_SHIP);
        final NavlogDao dao = getDbi().open(NavlogDao.class);
        try {
            dao.insert(journalId, currentShip, timestamp, toSystemId, distance, fuelUsed);
        } finally {
            dao.close();
        }
    }

    /**
     * Gibt das Navigationslog zurück
     * 
     * @return {@link List} of {@link VNavlog}
     */
    public List<VNavlog> getNavlog() {
        final VNavlogDao dao = getDbi().open(VNavlogDao.class);
        try {
            return dao.list();
        } finally {
            dao.close();
        }
    }

}
