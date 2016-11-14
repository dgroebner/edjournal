package de.dgroebner.edjson.model.data;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.json.JSONArray;

/**
 * Sternencoordinaten
 * 
 * @author dgroebner
 */
public class Coordinates {

    private final BigDecimal xAxis;

    private final BigDecimal yAxis;

    private final BigDecimal zAxis;

    /**
     * Erzeugt ein neues Coordinatenobjekt aus dem übergebenen JSONArray
     * 
     * @param coords {@link JSONArray}
     */
    public Coordinates(final JSONArray coords) {
        this.xAxis = coords.optBigDecimal(0, BigDecimal.ZERO);
        this.yAxis = coords.optBigDecimal(1, BigDecimal.ZERO);
        this.zAxis = coords.optBigDecimal(2, BigDecimal.ZERO);
    }

    /**
     * Liefert den X-Achsenwert zurück
     * 
     * @return {@link BigDecimal}
     */
    public final BigDecimal getxAxis() {
        return xAxis;
    }

    /**
     * Liefert den Y-Achsenwert zurück
     * 
     * @return {@link BigDecimal}
     */
    public final BigDecimal getyAxis() {
        return yAxis;
    }

    /**
     * Liefert den Z-Achsenwert zurück
     * 
     * @return {@link BigDecimal}
     */
    public final BigDecimal getzAxis() {
        return zAxis;
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.JSON_STYLE).toString();
    }

    /**
     * Liefert die Koordinaten als String zurück
     * 
     * @return {@link String}
     */
    public String getCoordString() {
        return String.format("%s:%s:%s", xAxis, yAxis, zAxis);
    }

}
