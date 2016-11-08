package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.JournalFileModel;

/**
 * Mapperklasse für die Datenbanktabelle 'journalfile'
 * 
 * @author dgroebner
 */
public class JournalFileMapper extends AbstractMapper<JournalFileModel> {

    public static final String COLUMN_FILENAME = "filename";

    public static final String COLUMN_READDATE = "readDate";

    @Override
    public JournalFileModel map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        return new JournalFileModel(r.getInt(COLUMN_ID), r.getString(COLUMN_FILENAME), r.getDate(COLUMN_READDATE));
    }

}
