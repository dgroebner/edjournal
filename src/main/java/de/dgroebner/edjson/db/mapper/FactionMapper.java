package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.DBFaction;

/**
 * Mapperklasse für die Datenbanktabelle 'fraction'
 * 
 * @author dgroebner
 */
public class FactionMapper extends AbstractMapper<DBFaction> {

    public static final String COLUMN_ALLEGIANCE = "allegiance";

    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_STATE = "state";

    @Override
    public DBFaction map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        /* @formatter:off */
        return DBFaction.builder()
                .id(r.getInt(COLUMN_ID))
                .journalId(r.getInt(AbstractMapper.COLUMN_JOURNAL_ID))
                .name(r.getString(COLUMN_NAME))
                .state(r.getString(COLUMN_STATE))
                .allegiance(r.getString(COLUMN_ALLEGIANCE))
                .inaraUrl(r.getString(COLUMN_INARA_URL))
                .build();
        /* @formatter:on */
    }

}
