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
        /* @formatter:off */
        return DBMaterial.builder()
                .id(r.getInt(COLUMN_ID))
                .edName(r.getString(COLUMN_ED_NAME))
                .inaraUrl(r.getString(COLUMN_INARA_URL))
                .kuerzel(r.getString(COLUMN_KUERZEL))
                .name(r.getString(COLUMN_NAME))
                .category(r.getString(COLUMN_CATEGORY))
                .stock(r.getInt(COLUMN_STOCK))
                .rarity(r.getString(COLUMN_RARITY))
                .build();
        /* @formatter:on */
    }

}
