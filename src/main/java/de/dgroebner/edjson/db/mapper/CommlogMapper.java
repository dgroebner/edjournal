package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.DBCommlog;

/**
 * Mapperklasse für die Datenbanktabelle 'commlog'
 * 
 * @author dgroebner
 */
public class CommlogMapper extends AbstractMapper<DBCommlog> {

    public static final String COLUMN_CHANNEL = "channel";

    public static final String COLUMN_TIMESTAMP = "timestamp";

    public static final String COLUMN_MESSAGE = "message";

    public static final String COLUMN_RECEIVER = "receiver";

    public static final String COLUMN_SENDER = "sender";

    @Override
    public DBCommlog map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        return new DBCommlog(r.getInt(COLUMN_ID), r.getInt(AbstractMapper.COLUMN_JOURNAL_ID), r.getTimestamp(
                COLUMN_TIMESTAMP).toLocalDateTime(), r.getString(COLUMN_SENDER), r.getString(COLUMN_RECEIVER),
                r.getString(COLUMN_CHANNEL), r.getString(COLUMN_MESSAGE));
    }

}
