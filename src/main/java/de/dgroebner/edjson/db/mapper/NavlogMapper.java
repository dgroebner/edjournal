package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.DBNavlog;

/**
 * Mapperklasse für die Datenbanktabelle 'navlog'
 * 
 * @author dgroebner
 */
public class NavlogMapper extends AbstractMapper<DBNavlog> {

    public static final String COLUMN_DISTANCE = "distance";

    public static final String COLUMN_FUEL_USED = "fuelused";

    public static final String COLUMN_SHIP_ID = "ship_id";

    public static final String COLUMN_TIMESTAMP = "timestamp";

    public static final String COLUMN_TOSYSTEM_ID = "tosystem_id";

    @Override
    public DBNavlog map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        /* @formatter:off */
        return DBNavlog.builder()
                .id(r.getInt(COLUMN_ID))
                .journalId(r.getInt(COLUMN_JOURNAL_ID))
                .shipId(r.getInt(COLUMN_SHIP_ID))
                .timestamp(r.getTimestamp(COLUMN_TIMESTAMP).toLocalDateTime())
                .tosystemId(r.getInt(COLUMN_TOSYSTEM_ID))
                .distance(r.getBigDecimal(COLUMN_DISTANCE))
                .fuelused(r.getBigDecimal(COLUMN_FUEL_USED))
                .build();
        /* @formatter:on */
    }

}
