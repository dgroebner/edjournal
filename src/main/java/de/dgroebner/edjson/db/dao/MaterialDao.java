package de.dgroebner.edjson.db.dao;

import static de.dgroebner.edjson.db.mapper.AbstractMapper.COLUMN_ID;
import static de.dgroebner.edjson.db.mapper.AbstractMapper.COLUMN_INARA_URL;
import static de.dgroebner.edjson.db.mapper.MaterialMapper.COLUMN_CATEGORY;
import static de.dgroebner.edjson.db.mapper.MaterialMapper.COLUMN_ED_NAME;
import static de.dgroebner.edjson.db.mapper.MaterialMapper.COLUMN_KUERZEL;
import static de.dgroebner.edjson.db.mapper.MaterialMapper.COLUMN_NAME;
import static de.dgroebner.edjson.db.mapper.MaterialMapper.COLUMN_STOCK;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.mapper.MaterialMapper;
import de.dgroebner.edjson.db.model.DBMaterial;

/**
 * DAO-Interface für die Tabelle 'material'
 * 
 * @author dgroebner
 */
@RegisterMapper(MaterialMapper.class)
public interface MaterialDao extends AbstractDao {

    /**
     * Gibt den Materialeintrag für den übergebenen Elite Dangerous Namen zurück
     * 
     * @param edName {@link String}
     * @return {@link String}
     */
    @SqlQuery("SELECT id, name, inara_url, kuerzel, ed_name, category, stock, rarity FROM material WHERE ed_name = :ed_name")
    DBMaterial findValueByEdName(@Bind(COLUMN_ED_NAME) String edName);

    /**
     * Gibt eine Liste der Materialen zurück
     * 
     * @return {@link List} von {@link DBMaterial}
     */
    @SqlQuery("SELECT id, name, inara_url, kuerzel, ed_name, category, stock, rarity FROM material ORDER BY category, name")
    List<DBMaterial> list();

    /**
     * Fügt eine Einstellung ein
     * 
     * @param name {@link String}
     * @param inaraUrl {@link String}
     * @param kuerzel {@link String}
     * @param edName {@link String}
     * @param category {@link String}
     * @param stock int
     * @return int
     */
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO material (name, inara_url, kuerzel, ed_name, category, stock) VALUES (:name, :inara_url, :kuerzel, :ed_name, :category, :stock)")
    int insert(@Bind(COLUMN_NAME) String name, @Bind(COLUMN_INARA_URL) String inaraUrl,
            @Bind(COLUMN_KUERZEL) String kuerzel, @Bind(COLUMN_ED_NAME) String edName,
            @Bind(COLUMN_CATEGORY) String category, @Bind(COLUMN_STOCK) int stock);
    
    /**
     * Aktualisiert die Einstellung für den übergebenen Entry
     * 
     * @param stock int
     * @param id int
     */
    @SqlUpdate("UPDATE material SET stock = :stock WHERE id = :id")
    void updateStockById(@Bind(COLUMN_STOCK) int stock, @Bind(COLUMN_ID) int id);
}
