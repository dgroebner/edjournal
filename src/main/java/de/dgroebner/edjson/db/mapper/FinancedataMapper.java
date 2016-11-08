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

    public static final String COLUMN_JOURNAL_ID = "journal_id";

    public static final String COLUMN_REMARK = "remark";

    public static final String COLUMN_VALUTADATUM = "valutadatum";

    @Override
    public DBFinancedata map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        return new DBFinancedata(r.getInt(COLUMN_ID), r.getInt(COLUMN_JOURNAL_ID), r.getTimestamp(COLUMN_VALUTADATUM)
                .toLocalDateTime(), r.getInt(COLUMN_AMOUNT), r.getString(COLUMN_CATEGORY), r.getString(COLUMN_REMARK));
    }

}
