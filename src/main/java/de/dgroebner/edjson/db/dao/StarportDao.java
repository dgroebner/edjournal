package de.dgroebner.edjson.db.dao;

import static de.dgroebner.edjson.db.mapper.AbstractMapper.COLUMN_INARA_URL;
import static de.dgroebner.edjson.db.mapper.AbstractMapper.COLUMN_JOURNAL_ID;
import static de.dgroebner.edjson.db.mapper.StarportMapper.COLUMN_ALLGIANCE;
import static de.dgroebner.edjson.db.mapper.StarportMapper.COLUMN_ECONOMY;
import static de.dgroebner.edjson.db.mapper.StarportMapper.COLUMN_FACTION_ID;
import static de.dgroebner.edjson.db.mapper.StarportMapper.COLUMN_GOVERNMENT;
import static de.dgroebner.edjson.db.mapper.StarportMapper.COLUMN_NAME;
import static de.dgroebner.edjson.db.mapper.StarportMapper.COLUMN_STARSYSTEMID;
import static de.dgroebner.edjson.db.mapper.StarportMapper.COLUMN_TYPE;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.mapper.StarportMapper;
import de.dgroebner.edjson.db.model.DBStarport;

/**
 * DAO-Interface für die Tabelle 'starport'
 * 
 * @author dgroebner
 */
@RegisterMapper(StarportMapper.class)
public interface StarportDao extends AbstractDao {

    /**
     * Fügt ein neuen Sternenhafen ein
     * 
     * @param journalId int
     * @param starsystemId int
     * @param name {@link String}
     * @param type {@link String}
     * @param inaraUrl {@link String}
     * @param factionId int
     * @param allegiance {@link String}
     * @param government {@link String}
     * @param economy {@link String}
     * @return int
     */
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO starport (journal_id, starsystem_id, name, type, inara_url, faction_id, allegiance, government, economy) VALUES (:journal_id, :starsystem_id, :name, :type, :inara_url, :faction_id, :allegiance, :government, :economy)")
    @SuppressWarnings("squid:S00107")
    int insert(@Bind(COLUMN_JOURNAL_ID) int journalId, @Bind(COLUMN_STARSYSTEMID) int starsystemId,
            @Bind(COLUMN_NAME) String name, @Bind(COLUMN_TYPE) String type, @Bind(COLUMN_INARA_URL) String inaraUrl,
            @Bind(COLUMN_FACTION_ID) int factionId, @Bind(COLUMN_ALLGIANCE) String allegiance,
            @Bind(COLUMN_GOVERNMENT) String government, @Bind(COLUMN_ECONOMY) String economy);

    /**
     * Aktualisiert die MetaDaten des Sternenhafens
     * 
     * @param factionId int
     * @param allegiance {@link String}
     * @param government {@link String}
     * @param economy {@link String}
     * @param name {@link String}
     */
    @SqlUpdate("UPDATE starport SET faction_id = :faction_id, allegiance = :allegiance, government = :government, economy = :economy WHERE name = :name")
    void updateMetaData(@Bind(COLUMN_FACTION_ID) int factionId, @Bind(COLUMN_ALLGIANCE) String allegiance,
            @Bind(COLUMN_GOVERNMENT) String government, @Bind(COLUMN_ECONOMY) String economy,
            @Bind(COLUMN_NAME) String name);

    /**
     * Selektiert ein Sternenhafen anhand des Namens
     * 
     * @param name {@link String}
     * @return DBStarsystem
     */
    @SqlQuery("SELECT id, journal_id, starsystem_id, name, type, inara_url, faction_id, allegiance, government, economy FROM starport WHERE name = :name")
    DBStarport findByName(@Bind(COLUMN_NAME) String name);

}
