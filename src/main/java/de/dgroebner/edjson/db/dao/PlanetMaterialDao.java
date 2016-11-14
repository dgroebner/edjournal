package de.dgroebner.edjson.db.dao;

import static de.dgroebner.edjson.db.mapper.AbstractMapper.COLUMN_JOURNAL_ID;
import static de.dgroebner.edjson.db.mapper.PlanetMaterialMapper.COLUMN_AMOUNT;
import static de.dgroebner.edjson.db.mapper.PlanetMaterialMapper.COLUMN_MATERIAL_ID;
import static de.dgroebner.edjson.db.mapper.PlanetMaterialMapper.COLUMN_PLANET_ID;

import java.math.BigDecimal;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.mapper.PlanetMaterialMapper;

/**
 * DAO-Interface für die Tabelle 'planet_material'
 * 
 * @author dgroebner
 */
@RegisterMapper(PlanetMaterialMapper.class)
public interface PlanetMaterialDao extends AbstractDao {

    /**
     * Fügt eine neue Planet-Material-Verknüpfung hinzu und gibt die erzeugte ID zurück
     * 
     * @param journalId int
     * @param planetId int
     * @param materialId int
     * @param ampunt {@link BigDecimal}
     * @return int
     */
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO planet_material (journal_id, planet_id, material_id, amount) VALUES (:journal_id, :planet_id, :material_id, :amount)")
    @SuppressWarnings("squid:S00107")
    int insert(@Bind(COLUMN_JOURNAL_ID) int journalId, @Bind(COLUMN_PLANET_ID) int planetId,
            @Bind(COLUMN_MATERIAL_ID) int materialId, @Bind(COLUMN_AMOUNT) BigDecimal ampunt);


}
