package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.DBPlanetMaterial;

/**
 * Mapperklasse für die Datenbanktabelle 'planet_material'
 * 
 * @author dgroebner
 */
public class PlanetMaterialMapper extends AbstractMapper<DBPlanetMaterial> {

    public static final String COLUMN_MATERIAL_ID = "material_id";

    public static final String COLUMN_PLANET_ID = "planet_id";

    public static final String COLUMN_AMOUNT = "amount";

    @Override
    public DBPlanetMaterial map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        /* @formatter:off */
        return DBPlanetMaterial.builder()
                .id(r.getInt(COLUMN_ID))
                .journalId(r.getInt(COLUMN_JOURNAL_ID))
                .planetId(r.getInt(COLUMN_PLANET_ID))
                .materialId(r.getInt(COLUMN_MATERIAL_ID))
                .amount(r.getBigDecimal(COLUMN_AMOUNT))
                .build();
        /* @formatter:on */
    }

}
