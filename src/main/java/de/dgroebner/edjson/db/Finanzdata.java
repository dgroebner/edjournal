package de.dgroebner.edjson.db;

import java.time.LocalDateTime;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.dao.FinancedataDao;

/**
 * Methoden für die Datenbanktabelle 'financdata' zur Speicherung der Finanzdaten
 * 
 * @author dgroebner
 */
public class Finanzdata extends AbstractDBTable {

    public enum CATEGORY {
        MISSING_SALES;
    }

    /**
     * Constructor.
     *
     * @param dbi {@link DBI}
     */
    public Finanzdata(final DBI dbi) {
        super(dbi);
    }

    /**
     * Schreibt einen neuen Journaleintrag in die Datenbank. Die ID des geschriebenen Datensatz kann anschließend über
     * die Methode {@link Finanzdata#getDatabaseId()} abgefragt werden.
     * 
     * @param journalId int
     * @param valutadatum {@link LocalDateTime}
     * @param amount int
     * @param category {@link String}
     * @param remark {@link String}
     */
    public void save(final int journalId, final LocalDateTime valutadatum, final int amount, final CATEGORY category,
            final String remark) {
        final FinancedataDao dao = getDbi().open(FinancedataDao.class);
        try {
            setId(dao.insert(journalId, valutadatum, amount, category.name(), remark));
        } finally {
            dao.close();
        }
    }

    /**
     * Selektiert das aktuelle Saldo
     * 
     * @return int
     */
    public int getSaldo() {
        final FinancedataDao dao = getDbi().open(FinancedataDao.class);
        try {
            return dao.selectSaldo();
        } finally {
            dao.close();
        }
    }

}
