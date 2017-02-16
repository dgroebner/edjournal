package de.dgroebner.edjson.db;

import java.time.LocalDateTime;
import java.util.List;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.dao.FinancedataDao;
import de.dgroebner.edjson.db.dao.VFinanceLogDao;
import de.dgroebner.edjson.db.model.DBFaction;
import de.dgroebner.edjson.db.model.VFinanceLog;

/**
 * Methoden für die Datenbanktabelle 'financdata' zur Speicherung der Finanzdaten
 * 
 * @author dgroebner
 */
public class Financedata extends AbstractDBTable {

    /**
     * Umsatzkategorien
     * 
     * @author dgroebner
     */
    public enum CATEGORY {
        CREW_COSTS, MISSING_SALES, MODUL_STORAGE, MODUL_COSTS, SHIP_COSTS, TRANSFERS, OPERATING_COSTS, EXPLORATION_DATA, MISSIONS, MARKET, BLACKMARKET, FINES, BOUNTIES, INSURANCE;
    }

    /**
     * Constructor.
     *
     * @param dbi {@link DBI}
     */
    public Financedata(final DBI dbi) {
        super(dbi);
    }

    /**
     * Schreibt einen neuen Journaleintrag in die Datenbank und gibt die id zurück
     * 
     * @param journalId int
     * @param valutadatum {@link LocalDateTime}
     * @param amount int
     * @param category {@link String}
     * @param remark {@link String}
     * @return int
     */
    public int save(final int journalId, final LocalDateTime valutadatum, final int amount, final CATEGORY category,
            final String remark) {
        final DBFaction currentLocalFaction = new Faction(getDbi()).getCurrentLocalFaction();
        final String factionName = currentLocalFaction.getName();
        final String factionState = currentLocalFaction.getState();
        return save(journalId, valutadatum, amount, category, remark, factionName, factionState);
    }

    /**
     * Schreibt einen neuen Journaleintrag in die Datenbank und gibt die id zurück.
     * 
     * @param journalId int
     * @param valutadatum {@link LocalDateTime}
     * @param amount int
     * @param category {@link String}
     * @param remark {@link String}
     * @param fractionName {@link String}
     * @param fractionState {@link String}
     * @return int
     */
    public int save(final int journalId, final LocalDateTime valutadatum, final int amount, final CATEGORY category,
            final String remark, final String fractionName, final String fractionState) {
        final DBFaction dbFraction = new Faction(getDbi()).writeOrGetFraction(journalId, fractionName, fractionState);
        final FinancedataDao dao = getDbi().open(FinancedataDao.class);
        try {
            return dao.insert(journalId, valutadatum, amount, category.name(), remark, dbFraction.getId());
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

    /**
     * Selektiert die letzten 50 Einträge des Finanzlogs
     * 
     * @return {@link List} von {@link VFinanceLog}
     */
    public List<VFinanceLog> listFinanceLog() {
        final VFinanceLogDao dao = getDbi().open(VFinanceLogDao.class);
        try {
            return dao.list();
        } finally {
            dao.close();
        }
    }

}
