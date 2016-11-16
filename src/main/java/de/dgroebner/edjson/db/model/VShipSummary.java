package de.dgroebner.edjson.db.model;

import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * Modellklasse für den View 'vshipsummary'
 * 
 * @author dgroebner
 */
public class VShipSummary extends AbstractModel {

    private int edId;

    private String type;

    private String callsign;

    private String inaraUrl;

    private BigDecimal distance;

    /**
     * Constructor.
     */
    public VShipSummary() {
        // Default Constructor
    }

    /**
     * Constructor.
     *
     * @param type {@link String}
     * @param callsign {@link String}
     * @param inaraUrl {@link String}
     * @param distance {@link BigDecimal}
     */
    public VShipSummary(final String type, final String callsign, final String inaraUrl, final BigDecimal distance) {
        super(0);
        this.type = type;
        this.callsign = callsign;
        this.inaraUrl = inaraUrl;
        this.distance = distance;
    }

    public final int getEdId() {
        return edId;
    }

    public final void setEdId(int edId) {
        this.edId = edId;
    }

    public final String getType() {
        return type;
    }

    public final void setType(String type) {
        this.type = type;
    }

    public final String getCallsign() {
        return callsign;
    }

    public final void setCallsign(String callsign) {
        this.callsign = callsign;
    }

    public final String getInaraUrl() {
        return inaraUrl;
    }

    public final void setInaraUrl(String inaraUrl) {
        this.inaraUrl = inaraUrl;
    }

    public final BigDecimal getDistance() {
        return distance.setScale(2, RoundingMode.HALF_UP);
    }

    public final void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

}
