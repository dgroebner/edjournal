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
        return new DBPlanetMaterial(r.getInt(COLUMN_ID), r.getInt(COLUMN_JOURNAL_ID), r.getInt(COLUMN_PLANET_ID),
                r.getInt(COLUMN_MATERIAL_ID), r.getBigDecimal(COLUMN_AMOUNT));
    }

}
