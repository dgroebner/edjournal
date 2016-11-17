package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.DBStarport;

/**
 * Mapperklasse für die Datenbanktabelle 'starport'
 * 
 * @author dgroebner
 */
public class StarportMapper extends AbstractMapper<DBStarport> {

    public static final String COLUMN_ALLGIANCE = "allegiance";

    public static final String COLUMN_ECONOMY = "economy";

    public static final String COLUMN_FACTION_ID = "faction_id";

    public static final String COLUMN_GOVERNMENT = "government";

    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_STARSYSTEMID = "starsystem_id";

    public static final String COLUMN_TYPE = "type";

    @Override
    public DBStarport map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        return new DBStarport(r.getInt(COLUMN_ID), r.getInt(COLUMN_JOURNAL_ID), r.getInt(COLUMN_STARSYSTEMID),
                r.getString(COLUMN_NAME), r.getString(COLUMN_TYPE), r.getString(COLUMN_INARA_URL),
                r.getInt(COLUMN_FACTION_ID), r.getString(COLUMN_ALLGIANCE), r.getString(COLUMN_GOVERNMENT),
                r.getString(COLUMN_ECONOMY));
    }

}
