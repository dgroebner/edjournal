package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.DBStarportVisits;

/**
 * Mapperklasse für die Datenbanktabelle 'starsystems_visits'
 * 
 * @author dgroebner
 */
public class StarportVisitsMapper extends AbstractMapper<DBStarportVisits> {

    public static final String COLUMN_SHIP_ID = "ship_id";

    public static final String COLUMN_STARPORTID = "starport_id";

    @Override
    public DBStarportVisits map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        /* @formatter:off */
        return DBStarportVisits.builder()
                .id(r.getInt(COLUMN_ID))
                .journalId(r.getInt(COLUMN_JOURNAL_ID))
                .starportId(r.getInt(COLUMN_STARPORTID))
                .shipId(r.getInt(COLUMN_SHIP_ID))
                .build();
        /* @formatter:on */
    }

}
