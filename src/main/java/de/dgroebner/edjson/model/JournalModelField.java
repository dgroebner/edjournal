package de.dgroebner.edjson.model;

/**
 * Interface für ein einzelnes Elite Dangerous Journalfeld
 * 
 * @author dgroebner
 */
public interface JournalModelField {

    /**
     * Gibt den Code des Feldes im Journal zurück
     * 
     * @return {@link String}
     */
    String getCode();

    /**
     * Gibt den Datentyp des Feldes im Journal zurück
     * 
     * @return {@link Class}
     */
    Class<?> getType();
}
