package de.dgroebner.edjson.db.dao;

import static de.dgroebner.edjson.db.mapper.PropertiesMapper.COLUMN_ENTRY;
import static de.dgroebner.edjson.db.mapper.PropertiesMapper.COLUMN_VALUE;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.mapper.PropertiesMapper;

/**
 * DAO-Interface für die Tabelle 'properties'
 * 
 * @author dgroebner
 */
@RegisterMapper(PropertiesMapper.class)
public interface PropertiesDao extends AbstractDao {

    /**
     * Gibt die Einstellung für den übergebenen Entry zurück
     * 
     * @param entry {@link String}
     * @return {@link String}
     */
    @SqlQuery("SELECT value FROM properties WHERE entry = :entry")
    String findValueByEntry(@Bind(COLUMN_ENTRY) String entry);

    /**
     * Fügt eine Einstellung ein
     * 
     * @param entry {@link String}
     * @param value {@link String}
     * @return int
     */
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO properties (entry, value) VALUES (:entry, :value)")
    int insert(@Bind(COLUMN_ENTRY) String entry, @Bind(COLUMN_VALUE) String value);
    
    /**
     * Aktualisiert die Einstellung für den übergebenen Entry
     * 
     * @param value {@link String}
     * @param entry {@link String}
     */
    @SqlUpdate("UPDATE properties SET value = :value WHERE entry = :entry")
    void updateEntry(@Bind(COLUMN_VALUE) String value, @Bind(COLUMN_ENTRY) String entry);

    /**
     * Löscht die Einstellung für den übergebenen Entry
     * 
     * @param entry {@link String}
     */
    @SqlUpdate("DELETE FROM properties WHERE entry = :entry")
    void deleteEntry(@Bind(COLUMN_ENTRY) String entry);
}
