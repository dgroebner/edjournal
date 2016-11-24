package de.dgroebner.edjson.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;

import de.dgroebner.edjson.db.model.DBMaterial;

/**
 * Mapperklasse für die Datenbanktabelle 'material'
 * 
 * @author dgroebner
 */
public class MaterialMapper extends AbstractMapper<DBMaterial> {

    public static final String COLUMN_CATEGORY = "category";

    public static final String COLUMN_ED_NAME = "ed_name";

    public static final String COLUMN_KUERZEL = "kuerzel";

    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_STOCK = "stock";

    public static final String COLUMN_RARITY = "rarity";

    @Override
    public DBMaterial map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        return new DBMaterial(r.getInt(COLUMN_ID), r.getString(COLUMN_NAME), r.getString(COLUMN_INARA_URL),
                r.getString(COLUMN_KUERZEL), r.getString(COLUMN_NAME), r.getString(COLUMN_CATEGORY),
                r.getInt(COLUMN_STOCK), r.getString(COLUMN_RARITY));
    }

}
