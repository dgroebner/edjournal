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
        /* @formatter:off */
        return VFinanceLog.builder()
                .valutadatum(r.getTimestamp(COLUMN_VALUTADATUM).toLocalDateTime())
                .amount(r.getInt(COLUMN_AMOUNT))
                .category(r.getString(COLUMN_CATEGORY))
                .remark(r.getString(COLUMN_REMARK))
                .factionName(r.getString(COLUMN_FACTIONNAME))
                .factionUrl(r.getString(COLUMN_FACTIONURL))
                .build();
        /* @formatter:on */
    }

}
