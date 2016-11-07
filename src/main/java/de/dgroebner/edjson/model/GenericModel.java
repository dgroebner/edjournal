package de.dgroebner.edjson.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import de.dgroebner.edjson.model.data.Coordinates;

/**
 * Abstrakte, generische Modellklasse für Elite Dangerous Journaleinträge
 * 
 * @author dgroebner
 * @param <F> {@link Enum} das {@link JournalModelField} erweitert
 */
public abstract class GenericModel<F extends Enum<? extends JournalModelField>> implements JournalModel {

    private final JSONObject json;

    private final Collection<F> fields;

    /**
     * Erzeugt ein neues Model aus dem übergebenen {@link JSONObject} mit den übergebnen Feldern.
     *
     * @param json {@link JSONObject}
     * @param fields {@link Collection}
     */
    protected GenericModel(final JSONObject json, final Collection<F> fields) {
        this.json = json;
        this.fields = fields;
    }

    @Override
    public String getValueAsString(final JournalModelField key) {
        return json.has(key.getCode()) ? json.getString(key.getCode()) : StringUtils.EMPTY;
    }

    @Override
    public int getValueAsInt(final JournalModelField key) {
        return json.has(key.getCode()) ? json.getInt(key.getCode()) : 0;
    }

    @Override
    public BigDecimal getValueAsBigDecimal(final JournalModelField key) {
        return json.has(key.getCode()) ? json.getBigDecimal(key.getCode()) : BigDecimal.ZERO;
    }

    @Override
    public boolean getValueAsBoolean(final JournalModelField key) {
        return json.has(key.getCode()) ? json.getBoolean(key.getCode()) : false;
    }

    @Override
    public JSONArray getValueAsJsonArray(final JournalModelField key) {
        return json.has(key.getCode()) ? json.getJSONArray(key.getCode()) : new JSONArray();
    }
    
    @Override
    public Coordinates getValueAsCoordinates(final JournalModelField key) {
        final JSONArray array = json.has(key.getCode()) ? json.getJSONArray(key.getCode()) : new JSONArray();
        return new Coordinates(array);
    }

    @Override
    public JSONObject getValueAsJsonObject(final JournalModelField key) {
        return json.has(key.getCode()) ? json.getJSONObject(key.getCode()) : new JSONObject();
    }

    @Override
    public LocalDateTime getValueAsLocalDateTime(final JournalModelField key) {
        if (!json.has(key.getCode())) {
            return LocalDateTime.MIN;
        }

        final String value = json.getString(key.getCode());
        return LocalDateTime.parse(value, DateTimeFormatter.ISO_DATE_TIME);
    }

    @Override
    public String toString() {
        final StringBuilder tostr = new StringBuilder(getClass().getName());
        tostr.append(" with Values: \n");
        fields.forEach(field -> addValueToStringBuilder(tostr, field));
        return tostr.toString();
    }

    /**
     * Gibt das Datenobjekt der aktuellen Klasse zurück
     * 
     * @return {@link JSONObject}
     */
    protected JSONObject getDataObject() {
        return json;
    }

    /**
     * Fügt as übergebene Feld abhängig von der Feldklasse dem {@link StringBuilder} hinzu
     * 
     * @param tostr {@link StringBuilder}
     * @param field F
     */
    private void addValueToStringBuilder(final StringBuilder tostr, final F field) {
        final JournalModelField fieldCasted = (JournalModelField) field;
        final Object value;

        if (fieldCasted.getType() == Integer.class) {
            value = Integer.valueOf(getValueAsInt(fieldCasted));
        } else if (fieldCasted.getType() == Boolean.class) {
            value = Boolean.valueOf(getValueAsBoolean(fieldCasted));
        } else if (fieldCasted.getType() == BigDecimal.class) {
            value = getValueAsBigDecimal(fieldCasted);
        } else if (fieldCasted.getType() == LocalDateTime.class) {
            value = getValueAsLocalDateTime(fieldCasted);
        } else if (fieldCasted.getType() == JSONArray.class) {
            value = getValueAsJsonArray(fieldCasted);
        } else if (fieldCasted.getType() == Coordinates.class) {
            value = getValueAsCoordinates(fieldCasted);
        } else if (fieldCasted.getType() == JSONObject.class) {
            value = getValueAsJsonObject(fieldCasted);
        } else {
            value = getValueAsString(fieldCasted);
        }

        tostr.append(fieldCasted.getCode()).append(": ").append(value).append("\n");
    }
}
