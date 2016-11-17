package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.VFinanceLog;

/**
 * Mapperklasse für den View 'vfinancelog'
 * 
 * @author dgroebner
 */
public class VFinanceLogMapper extends AbstractMapper<VFinanceLog> {

    public static final String COLUMN_AMOUNT = "amount";

    public static final String COLUMN_CATEGORY = "category";

    public static final String COLUMN_REMARK = "remark";

    public static final String COLUMN_VALUTADATUM = "valutadatum";

    public static final String COLUMN_FACTIONNAME = "factionname";

    public static final String COLUMN_FACTIONURL = "factionurl";

    @Override
    public VFinanceLog map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        return new VFinanceLog(r.getTimestamp(COLUMN_VALUTADATUM).toLocalDateTime(), r.getInt(COLUMN_AMOUNT),
                r.getString(COLUMN_CATEGORY), r.getString(COLUMN_REMARK), r.getString(COLUMN_FACTIONNAME),
                r.getString(COLUMN_FACTIONURL));
    }

}
