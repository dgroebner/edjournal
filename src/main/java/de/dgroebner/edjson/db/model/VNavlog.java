package de.dgroebner.edjson.db.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * Modellklasse für den View 'vnavlog'
 * 
 * @author dgroebner
 */
public class VNavlog extends AbstractModel {

    private String shipname;

    private String shiptype;

    private String shipUrl;

    private LocalDateTime timestamp;

    private String systemname;

    private String systemUrl;

    private BigDecimal distance;

    private BigDecimal fuelused;

    /**
     * Constructor.
     */
    public VNavlog() {
        // Default Constructor
    }

    /**
     * Constructor.
     * 
     * @param shipname {@link String}
     * @param shiptype {@link String}
     * @param shipUrl {@link String}
     * @param timestamp {@link LocalDateTime}
     * @param systemname {@link String}
     * @param systemUrl {@link String}
     * @param distance {@link BigDecimal}
     * @param fuelused {@link BigDecimal}
     */
    @SuppressWarnings("squid:S00107")
    public VNavlog(final String shipname, final String shiptype, final String shipUrl, final LocalDateTime timestamp,
            final String systemname, final String systemUrl, final BigDecimal distance, final BigDecimal fuelused) {
        super(0);
        this.shipname = shipname;
        this.timestamp = timestamp;
        this.shiptype = shiptype;
        this.shipUrl = shipUrl;
        this.systemname = systemname;
        this.systemUrl = systemUrl;
        this.distance = distance;
        this.fuelused = fuelused;
    }

    public final BigDecimal getDistance() {
        return distance;
    }

    public final void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public final BigDecimal getFuelused() {
        return fuelused;
    }

    public final void setFuelused(BigDecimal fuelused) {
        this.fuelused = fuelused;
    }

    public final LocalDateTime getTimestamp() {
        return timestamp;
    }

    public final void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public final String getShipname() {
        return shipname;
    }

    public final void setShipname(String shipname) {
        this.shipname = shipname;
    }

    public final String getShiptype() {
        return shiptype;
    }

    public final void setShiptype(String shiptype) {
        this.shiptype = shiptype;
    }

    public final String getShipUrl() {
        return shipUrl;
    }

    public final void setShipUrl(String shipUrl) {
        this.shipUrl = shipUrl;
    }

    public final String getSystemname() {
        return systemname;
    }

    public final void setSystemname(String systemname) {
        this.systemname = systemname;
    }

    public final String getSystemUrl() {
        return systemUrl;
    }

    public final void setSystemUrl(String systemUrl) {
        this.systemUrl = systemUrl;
    }

}
