package de.dgroebner.edjson.db.dao;

import static de.dgroebner.edjson.db.mapper.AbstractMapper.COLUMN_ID;
import static de.dgroebner.edjson.db.mapper.AbstractMapper.COLUMN_INARA_URL;
import static de.dgroebner.edjson.db.mapper.AbstractMapper.COLUMN_JOURNAL_ID;
import static de.dgroebner.edjson.db.mapper.FactionMapper.COLUMN_ALLEGIANCE;
import static de.dgroebner.edjson.db.mapper.FactionMapper.COLUMN_NAME;
import static de.dgroebner.edjson.db.mapper.FactionMapper.COLUMN_STATE;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.mapper.FactionMapper;
import de.dgroebner.edjson.db.model.DBFaction;

/**
 * DAO-Interface für die Tabelle 'faction'
 * 
 * @author dgroebner
 */
@RegisterMapper(FactionMapper.class)
public interface FactionDao extends AbstractDao {

    /**
     * Fügt einen neuen Fraktionseintrag hinzu und gibt die erzeugte ID zurück
     * 
     * @param journalId int
     * @param name {@link String}
     * @param state {@link String}
     * @param allegiance {@link String}
     * @param inaraUrl {@link String}
     * @return int
     */
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO faction (journal_id, name, state, allegiance, inara_url) VALUES (:journal_id, :name, :state, :allegiance, :inara_url)")
    int insert(@Bind(COLUMN_JOURNAL_ID) int journalId, @Bind(COLUMN_NAME) String name,
            @Bind(COLUMN_STATE) String state, @Bind(COLUMN_ALLEGIANCE) String allegiance,
            @Bind(COLUMN_INARA_URL) String inaraUrl);

    /**
     * Aktualisiert den Status der Fraktion
     * 
     * @param state {@link String}
     * @param id int
     */
    @SqlUpdate("UPDATE faction SET state = :state WHERE id = :id")
    void updateState(@Bind(COLUMN_STATE) String state, @Bind(COLUMN_ID) int id);

    /**
     * Gibt die Fraktion für den übergebenen Namen zurück
     * 
     * @param name {@link String}
     * @return {@link DBFaction}
     */
    @SqlQuery("SELECT id, journal_id, name, state, allegiance, inara_url FROM faction WHERE name = :name")
    DBFaction findByName(@Bind(COLUMN_NAME) String name);

    /**
     * Gibt die Fraktion für den übergebenen Namen zurück
     * 
     * @param id int
     * @return {@link DBFaction}
     */
    @SqlQuery("SELECT id, journal_id, name, state, allegiance, inara_url FROM faction WHERE id = :id")
    DBFaction findById(@Bind(COLUMN_ID) int id);

}
