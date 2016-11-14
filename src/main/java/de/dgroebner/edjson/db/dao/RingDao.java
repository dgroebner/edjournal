package de.dgroebner.edjson.db.dao;

import static de.dgroebner.edjson.db.mapper.AbstractMapper.COLUMN_JOURNAL_ID;
import static de.dgroebner.edjson.db.mapper.RingMapper.COLUMN_INNER_RAD;
import static de.dgroebner.edjson.db.mapper.RingMapper.COLUMN_MASS_MT;
import static de.dgroebner.edjson.db.mapper.RingMapper.COLUMN_NAME;
import static de.dgroebner.edjson.db.mapper.RingMapper.COLUMN_OUTER_RAD;
import static de.dgroebner.edjson.db.mapper.RingMapper.COLUMN_PLANET_ID;
import static de.dgroebner.edjson.db.mapper.RingMapper.COLUMN_STARSYSTEM_ID;
import static de.dgroebner.edjson.db.mapper.RingMapper.COLUMN_STAR_ID;
import static de.dgroebner.edjson.db.mapper.RingMapper.COLUMN_TYPE;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.mapper.RingMapper;

/**
 * DAO-Interface für die Tabelle 'ring'
 * 
 * @author dgroebner
 */
@RegisterMapper(RingMapper.class)
public interface RingDao extends AbstractDao {

    /**
     * Fügt einen neuen Ring hinzu und gibt die erzeugte ID zurück
     * 
     * @param journalId int
     * @param starsystemId int
     * @param starId int
     * @param name {@link String}
     * @param type {@link String}
     * @param massMt {@link String}
     * @param innerRad {@link String}
     * @param outerRad {@link String}
     * @return int
     */
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO ring (journal_id, starsystem_id, star_id, name, type, mass_mt, inner_rad, outer_rad) VALUES (:journal_id, :starsystem_id, :star_id, :name, :type, :mass_mt, :inner_rad, :outer_rad)")
    @SuppressWarnings("squid:S00107")
    int insertForStar(@Bind(COLUMN_JOURNAL_ID) int journalId, @Bind(COLUMN_STARSYSTEM_ID) int starsystemId,
            @Bind(COLUMN_STAR_ID) int starId, @Bind(COLUMN_NAME) String name, @Bind(COLUMN_TYPE) String type,
            @Bind(COLUMN_MASS_MT) String massMt, @Bind(COLUMN_INNER_RAD) String innerRad,
            @Bind(COLUMN_OUTER_RAD) String outerRad);

    /**
     * Fügt einen neuen Ring hinzu und gibt die erzeugte ID zurück
     * 
     * @param journalId int
     * @param starsystemId int
     * @param planetId int
     * @param name {@link String}
     * @param type {@link String}
     * @param massMt {@link String}
     * @param innerRad {@link String}
     * @param outerRad {@link String}
     * @return int
     */
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO ring (journal_id, starsystem_id, planet_id, name, type, mass_mt, inner_rad, outer_rad) VALUES (:journal_id, :starsystem_id, :planet_id, :name, :type, :mass_mt, :inner_rad, :outer_rad)")
    @SuppressWarnings("squid:S00107")
    int insertForPlanet(@Bind(COLUMN_JOURNAL_ID) int journalId, @Bind(COLUMN_STARSYSTEM_ID) int starsystemId,
            @Bind(COLUMN_PLANET_ID) int planetId, @Bind(COLUMN_NAME) String name, @Bind(COLUMN_TYPE) String type,
            @Bind(COLUMN_MASS_MT) String massMt, @Bind(COLUMN_INNER_RAD) String innerRad,
            @Bind(COLUMN_OUTER_RAD) String outerRad);


}
