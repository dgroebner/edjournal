package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.DBProperties;

/**
 * Mapperklasse für die Datenbanktabelle 'properties'
 * 
 * @author dgroebner
 */
public class PropertiesMapper extends AbstractMapper<DBProperties> {

    public static final String COLUMN_ENTRY = "entry";

    public static final String COLUMN_VALUE = "value";

    @Override
    public DBProperties map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        /* @formatter:off */
        return DBProperties.builder()
                .id(r.getInt(COLUMN_ID))
                .entry(r.getString(COLUMN_ENTRY))
                .value(r.getString(COLUMN_VALUE))
                .build();
        /* @formatter:on */
    }

}
