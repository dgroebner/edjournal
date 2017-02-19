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
        /* @formatter:off */
        return DBStarport.builder()
                .id(r.getInt(COLUMN_ID))
                .journalId(r.getInt(COLUMN_JOURNAL_ID))
                .starsystemId(r.getInt(COLUMN_STARSYSTEMID))
                .name(r.getString(COLUMN_NAME))
                .type(r.getString(COLUMN_TYPE))
                .inaraUrl(r.getString(COLUMN_INARA_URL))
                .factionId(r.getInt(COLUMN_FACTION_ID))
                .allegiance(r.getString(COLUMN_ALLGIANCE))
                .government(r.getString(COLUMN_GOVERNMENT))
                .economy(r.getString(COLUMN_ECONOMY))
                .build();
        /* @formatter:on */
    }

}
