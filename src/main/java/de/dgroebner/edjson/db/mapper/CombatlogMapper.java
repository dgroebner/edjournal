package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.DBCombatlog;

/**
 * Mapperklasse für die Datenbanktabelle 'combatlog'
 * 
 * @author dgroebner
 */
public class CombatlogMapper extends AbstractMapper<DBCombatlog> {

    public static final String COLUMN_ACTION = "action";

    public static final String COLUMN_ENEMY = "enemy";

    public static final String COLUMN_SHIP_ID = "ship_id";

    public static final String COLUMN_TIMESTAMP = "timestamp";

    public static final String COLUMN_FACTION_ID = "faction_id";

    public static final String COLUMN_REWARD = "reward";

    @Override
    public DBCombatlog map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        return new DBCombatlog(r.getInt(COLUMN_ID), r.getInt(COLUMN_JOURNAL_ID), r.getInt(COLUMN_SHIP_ID), r
                .getTimestamp(COLUMN_TIMESTAMP).toLocalDateTime(), r.getString(COLUMN_ACTION),
                r.getString(COLUMN_ENEMY), r.getInt(COLUMN_FACTION_ID), r.getInt(COLUMN_REWARD));
    }

}
