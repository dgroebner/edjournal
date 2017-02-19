package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.DBJournal;

/**
 * Mapperklasse für die Datenbanktabelle 'journal'
 * 
 * @author dgroebner
 */
public class JournalMapper extends AbstractMapper<DBJournal> {

    public static final String COLUMN_EVENT = "event";

    public static final String COLUMN_JOURNALFILE_ID = "journalfile_id";

    public static final String COLUMN_MESSAGE = "message";

    public static final String COLUMN_TIMESTAMP = "timestamp";

    @Override
    public DBJournal map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        /* @formatter:off */
        return DBJournal.builder()
                .id(r.getInt(COLUMN_ID))
                .event(r.getString(COLUMN_EVENT))
                .timestamp(r.getTimestamp(COLUMN_TIMESTAMP).toLocalDateTime())
                .journalfileId(r.getInt(COLUMN_JOURNALFILE_ID))
                .message(r.getString(COLUMN_MESSAGE))
                .build();
        /* @formatter:on */
    }

}
