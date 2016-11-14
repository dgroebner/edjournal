package de.dgroebner.edjson.db.dao;

import static de.dgroebner.edjson.db.mapper.AbstractMapper.COLUMN_ID;
import static de.dgroebner.edjson.db.mapper.AbstractMapper.COLUMN_INARA_URL;
import static de.dgroebner.edjson.db.mapper.ShipMapper.COLUMN_CALLSIGN;
import static de.dgroebner.edjson.db.mapper.ShipMapper.COLUMN_ED_ID;
import static de.dgroebner.edjson.db.mapper.ShipMapper.COLUMN_TYPE;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.mapper.ShipMapper;
import de.dgroebner.edjson.db.model.DBShip;

/**
 * DAO-Interface für die Tabelle 'ship'
 * 
 * @author dgroebner
 */
@RegisterMapper(ShipMapper.class)
public interface ShipDao extends AbstractDao {

    /**
     * Fügt einen neuen Schiffseintrag hinzu und gibt die erzeugte ID zurück
     * 
     * @param edId int
     * @param type {@link String}
     * @param callsign {@link String}
     * @param inaraUrl {@link String}
     * @return int
     */
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO ship (edId, type, callsign, inara_url) VALUES (:edId, :type, :callsign, :inara_url)")
    int insert(@Bind(COLUMN_ED_ID) int edId, @Bind(COLUMN_TYPE) String type, @Bind(COLUMN_CALLSIGN) String callsign,
            @Bind(COLUMN_INARA_URL) String inaraUrl);

    /**
     * Gibt das Schiff für die übergebenen ED-ID zurück
     * 
     * @param edId {@link String}
     * @return {@link DBShip}
     */
    @SqlQuery("SELECT id, edId, type, callsign, inara_url FROM ship WHERE edId = :edId")
    DBShip findByEdId(@Bind(COLUMN_ED_ID) int edId);

    /**
     * Gibt das Schiff für die übergebenen ID zurück
     * 
     * @param id {@link String}
     * @return {@link DBShip}
     */
    @SqlQuery("SELECT id, edId, type, callsign, inara_url FROM ship WHERE id = :id")
    DBShip findById(@Bind(COLUMN_ID) int id);

    /**
     * Löscht das Schiff mit der übergebenen id
     * 
     * @param id int
     */
    @SqlUpdate("DELETE FROM ship WHERE id = :id")
    void deleteById(@Bind(COLUMN_ID) int id);

}
