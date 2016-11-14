package de.dgroebner.edjson.db;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import com.google.common.primitives.Ints;

import de.dgroebner.edjson.db.dao.PropertiesDao;

/**
 * Methoden für die Datenbanktabelle 'properties' zur Speicherung der Einstellungen
 * 
 * @author dgroebner
 */
public class Properties extends AbstractDBTable {

    /**
     * Schlüsselwörter für Propertyeinträge
     * 
     * @author dgroebner
     */
    public enum ENTRIES {
        CURRENT_SHIP, CURRENT_COMMANDER, CURRENT_LOCAL_FACTION, CURRENT_STAR_SYSTEM;
    }

    /**
     * Constructor.
     *
     * @param dbi {@link DBI}
     */
    public Properties(final DBI dbi) {
        super(dbi);
    }

    /**
     * Schreibt eine neue Einstellung in die Datenbank, sofern noch nicht vorhanden
     * 
     * @param entry ENTRIES
     * @param value {@link String}
     */
    public void save(final ENTRIES entry, final String value) {
        final PropertiesDao dao = getDbi().open(PropertiesDao.class);
        try {
            final String storedValue = dao.findValueByEntry(entry.name());
            if (storedValue != null) {
                dao.updateEntry(value, entry.name());
            } else {
                dao.insert(entry.name(), value);
            }
        } finally {
            dao.close();
        }
    }

    /**
     * Schreibt eine neue Einstellung in die Datenbank, sofern noch nicht vorhanden
     * 
     * @param entry ENTRIES
     * @param value int
     */
    public void save(final ENTRIES entry, final int value) {
        save(entry, Integer.toString(value));
    }

    /**
     * Gibt den Wert für den übergebene Entry zurück
     * 
     * @param entry {@link String}
     * @return {@link String}
     */
    public String getValueForEntry(final ENTRIES entry) {
        final PropertiesDao dao = getDbi().open(PropertiesDao.class);
        try {
            return StringUtils.defaultString(dao.findValueByEntry(entry.name()));
        } finally {
            dao.close();
        }
    }

    /**
     * Gibt den Wert für den übergebene Entry als int zurück. {@link Integer#MIN_VALUE} wenn kein Wert gespeichert ist
     * 
     * @param entry {@link String}
     * @return int
     */
    public int getIntValueForEntry(final ENTRIES entry) {
        final Integer value = Ints.tryParse(getValueForEntry(entry));
        return value != null ? value.intValue() : Integer.MIN_VALUE;
    }

    /**
     * Löscht den Wert für das übergebene Entry
     * 
     * @param entry {@link String}
     */
    public void deleteEntry(final ENTRIES entry) {
        final PropertiesDao dao = getDbi().open(PropertiesDao.class);
        try {
            dao.deleteEntry(entry.name());
        } finally {
            dao.close();
        }
    }

}
