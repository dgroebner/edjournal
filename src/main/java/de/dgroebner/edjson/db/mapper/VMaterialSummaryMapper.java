package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.VMaterialSummary;

/**
 * Mapperklasse für den View 'vmaterial_summary'
 * 
 * @author dgroebner
 */
public class VMaterialSummaryMapper extends AbstractMapper<VMaterialSummary> {

    public static final String COLUMN_MATERIAL = "material";

    public static final String COLUMN_AMOUNT = "amount";

    public static final String COLUMN_PLANET = "planet";

    public static final String COLUMN_PLANETTYPE = "planet_type";

    public static final String COLUMN_GRAVITY = "gravity";

    public static final String COLUMN_MATERIAL_URL = "material_url";

    public static final String COLUMN_STARSYSTEM_URL = "starsystem_url";

    public static final String COLUMN_DISTANCE_IN_SYSTEM = "distanceInsystem";

    public static final String COLUMN_DISTANCETOLUKU = "distanceToluku";

    @Override
    public VMaterialSummary map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        /* @formatter:off */
        return VMaterialSummary.builder()
                .material(r.getString(COLUMN_MATERIAL))
                .amount(r.getBigDecimal(COLUMN_AMOUNT))
                .planet(r.getString(COLUMN_PLANET))
                .planetType(r.getString(COLUMN_PLANETTYPE))
                .gravity(r.getBigDecimal(COLUMN_GRAVITY))
                .materialUrl(r.getString(COLUMN_MATERIAL_URL))
                .starsystemUrl(r.getString(COLUMN_STARSYSTEM_URL))
                .distanceInSystem(r.getBigDecimal(COLUMN_DISTANCE_IN_SYSTEM))
                .distanceToluku(r.getBigDecimal(COLUMN_DISTANCETOLUKU))
                .build();
        /* @formatter:on */
    }

}
