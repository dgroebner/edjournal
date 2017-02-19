package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.DBStarsystemVisits;

/**
 * Mapperklasse für die Datenbanktabelle 'starport_visits'
 * 
 * @author dgroebner
 */
public class StarsystemVisitsMapper extends AbstractMapper<DBStarsystemVisits> {

    public static final String COLUMN_SHIP_ID = "ship_id";

    public static final String COLUMN_STARSYSTEMID = "starsystem_id";

    @Override
    public DBStarsystemVisits map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        /* @formatter:off */
        return DBStarsystemVisits.builder()
                .id(r.getInt(COLUMN_ID))
                .journalId(r.getInt(COLUMN_JOURNAL_ID))
                .starsystemId(r.getInt(COLUMN_STARSYSTEMID))
                .shipId(r.getInt(COLUMN_SHIP_ID))
                .build();
        /* @formatter:on */
    }

}
