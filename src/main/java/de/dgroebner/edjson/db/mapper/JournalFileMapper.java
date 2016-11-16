package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.DBJournalFile;

/**
 * Mapperklasse für die Datenbanktabelle 'journalfile'
 * 
 * @author dgroebner
 */
public class JournalFileMapper extends AbstractMapper<DBJournalFile> {

    public static final String COLUMN_FILENAME = "filename";

    public static final String COLUMN_READDATE = "readDate";

    @Override
    public DBJournalFile map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        return new DBJournalFile(r.getInt(COLUMN_ID), r.getString(COLUMN_FILENAME), r.getTimestamp(COLUMN_READDATE)
                .toLocalDateTime());
    }

}
