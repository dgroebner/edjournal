package de.dgroebner.edjson.db.dao;

import static de.dgroebner.edjson.db.mapper.FinancedataMapper.COLUMN_AMOUNT;
import static de.dgroebner.edjson.db.mapper.FinancedataMapper.COLUMN_CATEGORY;
import static de.dgroebner.edjson.db.mapper.FinancedataMapper.COLUMN_JOURNAL_ID;
import static de.dgroebner.edjson.db.mapper.FinancedataMapper.COLUMN_REMARK;
import static de.dgroebner.edjson.db.mapper.FinancedataMapper.COLUMN_VALUTADATUM;

import java.time.LocalDateTime;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.binder.LocalDateTimeBinder;
import de.dgroebner.edjson.db.mapper.FinancedataMapper;

/**
 * DAO-Interface für die Tabelle 'financedata'
 * 
 * @author dgroebner
 */
@RegisterMapper(FinancedataMapper.class)
public interface FinancedataDao extends AbstractDao {

    /**
     * Fügt einen neuen Finanzdateneintrag hinzu und gibt die erzeugte ID zurück
     * 
     * @param journalId {@link String}
     * @param valutadatum {@link LocalDateTime}
     * @param amount int
     * @param category {@link String}
     * @param remark {@link String}
     * @return int
     */
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO financedata (journal_id, valutadatum, amount, category, remark) VALUES (:journal_id, :valutadatum, :amount, :category, :remark)")
    int insert(@Bind(COLUMN_JOURNAL_ID) int journalId,
            @Bind(value = COLUMN_VALUTADATUM, binder = LocalDateTimeBinder.class) LocalDateTime valutadatum,
            @Bind(COLUMN_AMOUNT) int amount, @Bind(COLUMN_CATEGORY) String category, @Bind(COLUMN_REMARK) String remark);

    /**
     * Selektiert das aktuelle Saldo der Finanzdaten
     * 
     * @return int
     */
    @SqlQuery("SELECT SUM(amount) FROM financedata")
    int selectSaldo();

}
