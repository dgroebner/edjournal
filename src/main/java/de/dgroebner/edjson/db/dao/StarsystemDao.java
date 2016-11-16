package de.dgroebner.edjson.db.dao;

import static de.dgroebner.edjson.db.mapper.AbstractMapper.COLUMN_ID;
import static de.dgroebner.edjson.db.mapper.AbstractMapper.COLUMN_INARA_URL;
import static de.dgroebner.edjson.db.mapper.AbstractMapper.COLUMN_JOURNAL_ID;
import static de.dgroebner.edjson.db.mapper.StarsystemMapper.COLUMN_ALLGIANCE;
import static de.dgroebner.edjson.db.mapper.StarsystemMapper.COLUMN_ECONOMY;
import static de.dgroebner.edjson.db.mapper.StarsystemMapper.COLUMN_FACTION_ID;
import static de.dgroebner.edjson.db.mapper.StarsystemMapper.COLUMN_GOVERNMENT;
import static de.dgroebner.edjson.db.mapper.StarsystemMapper.COLUMN_NAME;
import static de.dgroebner.edjson.db.mapper.StarsystemMapper.COLUMN_SECURITY;
import static de.dgroebner.edjson.db.mapper.StarsystemMapper.COLUMN_STARPOS;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.mapper.StarsystemMapper;
import de.dgroebner.edjson.db.model.DBStarsystem;

/**
 * DAO-Interface für die Tabelle 'starsystem'
 * 
 * @author dgroebner
 */
@RegisterMapper(StarsystemMapper.class)
public interface StarsystemDao extends AbstractDao {

    /**
     * Fügt ein neues Sternensystem ein
     * 
     * @param journalId int
     * @param name {@link String}
     * @param inaraUrl {@link String}
     * @param factionId int
     * @param security {@link String}
     * @param allegiance {@link String}
     * @param government {@link String}
     * @param economy {@link String}
     * @param starpos {@link String}
     * @return int
     */
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO starsystem (journal_id, name, inara_url, faction_id, security, allegiance, government, economy, starpos) VALUES (:journal_id, :name, :inara_url, :faction_id, :security, :allegiance, :government, :economy, :starpos)")
    @SuppressWarnings("squid:S00107")
    int insert(@Bind(COLUMN_JOURNAL_ID) int journalId, @Bind(COLUMN_NAME) String name,
            @Bind(COLUMN_INARA_URL) String inaraUrl, @Bind(COLUMN_FACTION_ID) int factionId,
            @Bind(COLUMN_SECURITY) String security, @Bind(COLUMN_ALLGIANCE) String allegiance,
            @Bind(COLUMN_GOVERNMENT) String government, @Bind(COLUMN_ECONOMY) String economy,
            @Bind(COLUMN_STARPOS) String starpos);
    
    /**
     * Aktualisiert die MetaDaten des Sternensystems
     * 
     * @param factionId int
     * @param security {@link String}
     * @param allegiance {@link String}
     * @param government {@link String}
     * @param economy {@link String}
     * @param name {@link String}
     * @param starpos {@link String}
     */
    @SqlUpdate("UPDATE starsystem SET faction_id = :faction_id, security = :security, allegiance = :allegiance, government = :government, economy = :economy, starpos = :starpos WHERE name = :name")
    void updateMetaData(@Bind(COLUMN_FACTION_ID) int factionId, @Bind(COLUMN_SECURITY) String security,
            @Bind(COLUMN_ALLGIANCE) String allegiance, @Bind(COLUMN_GOVERNMENT) String government,
            @Bind(COLUMN_ECONOMY) String economy, @Bind(COLUMN_STARPOS) String starpos, @Bind(COLUMN_NAME) String name);

    /**
     * Selektiert ein Sternensystem anhand des Namens
     * 
     * @param name {@link String}
     * @return DBStarsystem
     */
    @SqlQuery("SELECT id, journal_id, name, inara_url, faction_id, security, allegiance, government, economy, starpos FROM starsystem WHERE name = :name")
    DBStarsystem findByName(@Bind(COLUMN_NAME) String name);

    /**
     * Selektiert ein Sternensystem anhand des Namens
     * 
     * @param id int
     * @return DBStarsystem
     */
    @SqlQuery("SELECT id, journal_id, name, inara_url, faction_id, security, allegiance, government, economy, starpos FROM starsystem WHERE id = :id")
    DBStarsystem findById(@Bind(COLUMN_ID) int id);

}
