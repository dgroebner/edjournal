package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.VCombatlog;

/**
 * Mapperklasse für den View 'vcombatlog'
 * 
 * @author dgroebner
 */
public class VCombatlogMapper extends AbstractMapper<VCombatlog> {

    public static final String COLUMN_ACTION = "action";

    public static final String COLUMN_ENEMY = "enemy";

    public static final String COLUMN_SHIPNAME = "shipname";

    public static final String COLUMN_SHIP_URL = "ship_url";

    public static final String COLUMN_TIMESTAMP = "timestamp";

    public static final String COLUMN_FACTIONNAME = "faction_name";

    public static final String COLUMN_FACTION_URL = "faction_url";

    @Override
    public VCombatlog map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        return new VCombatlog(r.getTimestamp(COLUMN_TIMESTAMP).toLocalDateTime(), r.getString(COLUMN_ACTION),
                r.getString(COLUMN_ENEMY), r.getString(COLUMN_SHIPNAME), r.getString(COLUMN_SHIP_URL),
                r.getString(COLUMN_FACTIONNAME), r.getString(COLUMN_FACTION_URL));
    }

}
