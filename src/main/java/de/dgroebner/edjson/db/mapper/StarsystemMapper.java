package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.DBStarsystem;

/**
 * Mapperklasse für die Datenbanktabelle 'starsystem'
 * 
 * @author dgroebner
 */
public class StarsystemMapper extends AbstractMapper<DBStarsystem> {

    public static final String COLUMN_ALLGIANCE = "allegiance";

    public static final String COLUMN_ECONOMY = "economy";

    public static final String COLUMN_FACTION_ID = "faction_id";

    public static final String COLUMN_GOVERNMENT = "government";

    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_SECURITY = "security";

    public static final String COLUMN_STARPOS = "starpos";

    @Override
    public DBStarsystem map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        /* @formatter:off */
        return DBStarsystem.builder()
                .id(r.getInt(COLUMN_ID))
                .journalId(r.getInt(COLUMN_JOURNAL_ID))
                .name(r.getString(COLUMN_NAME))
                .inaraUrl(r.getString(COLUMN_INARA_URL))
                .factionId(r.getInt(COLUMN_FACTION_ID))
                .security(r.getString(COLUMN_SECURITY))
                .allegiance(r.getString(COLUMN_ALLGIANCE))
                .government(r.getString(COLUMN_GOVERNMENT))
                .economy(r.getString(COLUMN_ECONOMY))
                .starpos(r.getString(COLUMN_STARPOS))
                .build();
        /* @formatter:on */
    }

}
