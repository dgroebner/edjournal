package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.VStarsystemLog;

/**
 * Mapperklasse für den View 'vstarsystemslog'
 * 
 * @author dgroebner
 */
public class VStarsystemLogMapper extends AbstractMapper<VStarsystemLog> {

    public static final String COLUMN_ALLGIANCE = "allegiance";

    public static final String COLUMN_ECONOMY = "economy";

    public static final String COLUMN_SYSTEMNAME = "systemname";

    public static final String COLUMN_SYSTEM_URL = "system_url";

    public static final String COLUMN_VISITS = "visits";

    public static final String COLUMN_FACTIONNAME = "factionname";

    public static final String COLUMN_FACTION_URL = "faction_url";

    public static final String COLUMN_GOVERNMENT = "government";

    public static final String COLUMN_SECURITY = "security";

    public static final String COLUMN_STARPOS = "starpos";

    @Override
    public VStarsystemLog map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        return new VStarsystemLog(r.getInt(COLUMN_VISITS), r.getString(COLUMN_SYSTEMNAME),
                r.getString(COLUMN_SYSTEM_URL), r.getString(COLUMN_FACTIONNAME), r.getString(COLUMN_FACTION_URL),
                r.getString(COLUMN_SECURITY), r.getString(COLUMN_ALLGIANCE), r.getString(COLUMN_GOVERNMENT),
                r.getString(COLUMN_ECONOMY), r.getString(COLUMN_STARPOS));
    }

}
