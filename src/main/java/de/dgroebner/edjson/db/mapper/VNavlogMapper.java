package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.VNavlog;

/**
 * Mapperklasse für den View 'vnavlog'
 * 
 * @author dgroebner
 */
public class VNavlogMapper extends AbstractMapper<VNavlog> {

    public static final String COLUMN_DISTANCE = "distance";

    public static final String COLUMN_DISTANCETOLUKU = "distanceToluku";

    public static final String COLUMN_FUEL_USED = "fuelused";

    public static final String COLUMN_SHIPNAME = "shipname";

    public static final String COLUMN_TIMESTAMP = "timestamp";

    public static final String COLUMN_SHIPTYPE = "shiptype";

    public static final String COLUMN_SHIP_URL = "ship_url";

    public static final String COLUMN_SYSTEMNAME = "systemname";

    public static final String COLUMN_SYSTEM_URL = "system_url";

    @Override
    public VNavlog map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        /* @formatter:off */
        return VNavlog.builder()
                .shipname(r.getString(COLUMN_SHIPNAME))
                .shiptype(r.getString(COLUMN_SHIPTYPE))
                .shipUrl(r.getString(COLUMN_SHIP_URL))
                .timestamp(r.getTimestamp(COLUMN_TIMESTAMP).toLocalDateTime())
                .systemname(r.getString(COLUMN_SYSTEMNAME))
                .systemUrl(r.getString(COLUMN_SYSTEM_URL))
                .distance(r.getBigDecimal(COLUMN_DISTANCE))
                .fuelused( r.getBigDecimal(COLUMN_FUEL_USED))
                .distanceToluku(r.getBigDecimal(COLUMN_DISTANCETOLUKU))
                .build();
        /* @formatter:on */
    }

}
