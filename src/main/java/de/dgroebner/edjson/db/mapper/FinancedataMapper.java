package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.DBFinancedata;

/**
 * Mapperklasse für die Datenbanktabelle 'financedata'
 * 
 * @author dgroebner
 */
public class FinancedataMapper extends AbstractMapper<DBFinancedata> {

    public static final String COLUMN_AMOUNT = "amount";

    public static final String COLUMN_CATEGORY = "category";

    public static final String COLUMN_REMARK = "remark";

    public static final String COLUMN_VALUTADATUM = "valutadatum";

    public static final String COLUMN_FACTION_ID = "faction_id";

    @Override
    public DBFinancedata map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        /* @formatter:off */
        return DBFinancedata.builder()
                .id(r.getInt(COLUMN_ID))
                .journalId(r.getInt(COLUMN_JOURNAL_ID))
                .valutadatum(r.getTimestamp(COLUMN_VALUTADATUM).toLocalDateTime())
                .amount(r.getInt(COLUMN_AMOUNT))
                .category(r.getString(COLUMN_CATEGORY))
                .remark(r.getString(COLUMN_REMARK))
                .factionId(r.getInt(COLUMN_FACTION_ID))
                .build();
        /* @formatter:on */
    }

}
