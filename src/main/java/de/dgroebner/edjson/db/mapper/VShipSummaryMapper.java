package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.VShipSummary;

/**
 * Mapperklasse für den View 'vshipsummary'
 * 
 * @author dgroebner
 */
public class VShipSummaryMapper extends AbstractMapper<VShipSummary> {

    public static final String COLUMN_TYPE = "type";

    public static final String COLUMN_CALLSIGN = "callsign";

    public static final String COLUMN_DISTANCE = "distance";

    @Override
    public VShipSummary map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        /* @formatter:off */
        return VShipSummary.builder()
                .type(r.getString(COLUMN_TYPE))
                .callsign(r.getString(COLUMN_CALLSIGN))
                .inaraUrl(r.getString(COLUMN_INARA_URL))
                .distance(r.getBigDecimal(COLUMN_DISTANCE))
                .build();
        /* @formatter:on */
    }

}
