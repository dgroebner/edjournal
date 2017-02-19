package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.VRings;

/**
 * Mapperklasse für den View 'vrings'
 * 
 * @author dgroebner
 */
public class VRingMapper extends AbstractMapper<VRings> {

    public static final String COLUMN_ART = "art";

    public static final String COLUMN_STARSYSTEMNAME = "starsystemname";

    public static final String COLUMN_RINGNAME = "ringname";

    public static final String COLUMN_RINGTYPE = "ringtype";

    public static final String COLUMN_TYPE = "type";

    public static final String COLUMN_STARSYSTEM_URL = "starsystem_url";

    public static final String COLUMN_DISTANCE_IN_SYSTEM = "distanceInsystem";

    public static final String COLUMN_DISTANCETOLUKU = "distanceToluku";

    @Override
    public VRings map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        /* @formatter:off */
        return VRings.builder()
                .art(r.getString(COLUMN_ART))
                .starsystemname(r.getString(COLUMN_STARSYSTEMNAME))
                .ringname(r.getString(COLUMN_RINGNAME))
                .ringtype(r.getString(COLUMN_RINGTYPE))
                .starsystemUrl(r.getString(COLUMN_STARSYSTEM_URL))
                .distanceInSystem(r.getBigDecimal(COLUMN_DISTANCE_IN_SYSTEM))
                .distanceToluku(r.getBigDecimal(COLUMN_DISTANCETOLUKU))
                .build();
        /* @formatter:on */
    }

}
