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
        return new DBJournal(r.getInt(COLUMN_ID), r.getInt(COLUMN_JOURNALFILE_ID), r.getTimestamp(COLUMN_TIMESTAMP)
                .toLocalDateTime(), r.getString(COLUMN_EVENT), r.getString(COLUMN_MESSAGE));
    }

}
