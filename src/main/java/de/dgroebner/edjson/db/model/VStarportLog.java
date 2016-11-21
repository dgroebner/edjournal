package de.dgroebner.edjson.db.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * Modellklasse für den View 'vstarportlog'
 * 
 * @author dgroebner
 */
public class VStarportLog extends AbstractModel {

    private LocalDateTime timestamp;

    private String portname;

    private String portUrl;

    private String systemname;

    private String systemUrl;

    private String factionName;

    private String factionUrl;

    private String type;

    private String allegiance;

    private String government;

    private String economy;

    private BigDecimal distanceToluku;

    /**
     * Constructor.
     */
    public VStarportLog() {
        // Default Constructor
    }

    /**
     * Constructor.
     * 
     * @param timestamp {@link LocalDateTime}
     * @param portname {@link String}
     * @param portUrl {@link String}
     * @param systemname {@link String}
     * @param systemUrl {@link String}
     * @param factionName {@link String}
     * @param factionUrl {@link String}
     * @param type {@link String}
     * @param allegiance {@link String}
     * @param government {@link String}
     * @param economy {@link String}
     * @param distanceToluku {@link BigDecimal}
     */
    @SuppressWarnings("squid:S00107")
    public VStarportLog(final LocalDateTime timestamp, final String portname, final String portUrl,
            final String systemname, final String systemUrl, final String factionName, final String factionUrl,
            final String type, final String allegiance, final String government, final String economy,
            final BigDecimal distanceToluku) {
        super(0);
        this.timestamp = timestamp;
        this.systemUrl = systemUrl;
        this.portname = portname;
        this.portUrl = portUrl;
        this.systemname = systemname;
        this.factionName = factionName;
        this.allegiance = allegiance;
        this.factionUrl = factionUrl;
        this.type = type;
        this.government = government;
        this.economy = economy;
        this.distanceToluku = distanceToluku;
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

    public final String getFactionName() {
        return factionName;
    }

    public final void setFactionName(String factionName) {
        this.factionName = factionName;
    }

    public final String getFactionUrl() {
        return factionUrl;
    }

    public final void setFactionUrl(String factionUrl) {
        this.factionUrl = factionUrl;
    }

    public final String getAllegiance() {
        return allegiance;
    }

    public final void setAllegiance(String allegiance) {
        this.allegiance = allegiance;
    }

    public final String getGovernment() {
        return government;
    }

    public final void setGovernment(String government) {
        this.government = government;
    }

    public final String getEconomy() {
        return economy;
    }

    public final void setEconomy(String economy) {
        this.economy = economy;
    }

    public final LocalDateTime getTimestamp() {
        return timestamp;
    }

    public final void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public final String getPortname() {
        return portname;
    }

    public final void setPortname(String portname) {
        this.portname = portname;
    }

    public final String getPortUrl() {
        return portUrl;
    }

    public final void setPortUrl(String portUrl) {
        this.portUrl = portUrl;
    }

    public final String getType() {
        return type;
    }

    public final void setType(String type) {
        this.type = type;
    }

    public BigDecimal getDistanceToluku() {
        return distanceToluku;
    }

    public void setDistanceToluku(BigDecimal distanceToluku) {
        this.distanceToluku = distanceToluku;
    }

}
