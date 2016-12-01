package de.dgroebner.edjson.db.model;

import java.time.LocalDateTime;


/**
 * Modellklasse für den View 'vcombatlog'
 * 
 * @author dgroebner
 */
public class VCombatlog extends AbstractModel {

    private LocalDateTime timestamp;

    private String action;

    private String enemy;

    private String factionName;

    private String factionUrl;

    private String shipName;

    private String shipUrl;

    /**
     * Constructor.
     */
    public VCombatlog() {
        // Default Constructor
    }

    /**
     * Constructor.
     *
     * @param timestamp {@link LocalDateTime}
     * @param action {@link String}
     * @param enemy {@link String}
     * @param factionName {@link String}
     * @param factionUrl {@link String}
     * @param shipName {@link String}
     * @param shipUrl {@link String}
     */
    @SuppressWarnings("squid:S00107")
    public VCombatlog(final LocalDateTime timestamp, final String action, final String enemy, final String shipName,
            final String shipUrl, final String factionName, final String factionUrl) {
        super(0);
        this.timestamp = timestamp;
        this.timestamp = timestamp;
        this.action = action;
        this.enemy = enemy;
        this.factionName = factionName;
        this.factionUrl = factionUrl;
        this.shipName = shipName;
        this.shipUrl = shipUrl;
    }

    public final LocalDateTime getTimestamp() {
        return timestamp;
    }

    public final void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public final String getAction() {
        return action;
    }

    public final void setAction(String action) {
        this.action = action;
    }

    public final String getEnemy() {
        return enemy;
    }

    public final void setEnemy(String enemy) {
        this.enemy = enemy;
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

    public final String getShipName() {
        return shipName;
    }

    public final void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public final String getShipUrl() {
        return shipUrl;
    }

    public final void setShipUrl(String shipUrl) {
        this.shipUrl = shipUrl;
    }

}
