package de.dgroebner.edjson.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;

import de.dgroebner.edjson.model.data.Coordinates;

/**
 * Modelinterface für Elite Dangerous Journaleinträge
 * 
 * @author dgroebner
 */
public interface JournalModel {

    /**
     * Liefert den Wert für den übergebenen Schlüssel als String
     * 
     * @param key {@link JournalModelField}
     * @return {@link String}
     */
    String getValueAsString(final JournalModelField key);

    /**
     * Liefert den Wert für den übergebenen Schlüssel als int
     * 
     * @param key {@link JournalModelField}
     * @return int
     */
    int getValueAsInt(final JournalModelField key);

    /**
     * Liefert den Wert für den übergebenen Schlüssel als boolean
     * 
     * @param key {@link JournalModelField}
     * @return boolean
     */
    boolean getValueAsBoolean(final JournalModelField key);

    /**
     * Liefert den Wert für den übergebenen Schlüssel als {@link BigDecimal}
     * 
     * @param key {@link JournalModelField}
     * @return {@link BigDecimal}
     */
    BigDecimal getValueAsBigDecimal(final JournalModelField key);

    /**
     * Liefert den Wert für den übergebenen Schlüssel als {@link JSONArray}
     * 
     * @param key {@link JournalModelField}
     * @return {@link JSONArray}
     */
    JSONArray getValueAsJsonArray(final JournalModelField key);

    /**
     * Liefert den Wert für den übergebenen Schlüssel als {@link Coordinates}
     * 
     * @param key {@link JournalModelField}
     * @return {@link Coordinates}
     */
    Coordinates getValueAsCoordinates(JournalModelField key);

    /**
     * Liefert den Wert für den übergebenen Schlüssel als {@link JSONObject}
     * 
     * @param key {@link JournalModelField}
     * @return {@link JSONObject}
     */
    JSONObject getValueAsJsonObject(JournalModelField key);

    /**
     * Liefert den Wert für den übergebenen Schlüssel als {@link LocalDateTime}
     * 
     * @param key {@link JournalModelField}
     * @return {@link LocalDateTime}
     */
    LocalDateTime getValueAsLocalDateTime(final JournalModelField key);

    /**
     * Gibt den Logger der Implementeriung zurück
     * 
     * @return {@link Logger}
     */
    Logger getLogger();

}
