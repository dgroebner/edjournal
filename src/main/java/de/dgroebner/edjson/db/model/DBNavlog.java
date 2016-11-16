package de.dgroebner.edjson.db.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * Modellklasse für die Datenbanktabelle 'navlog'
 * 
 * @author dgroebner
 */
public class DBNavlog extends AbstractModel {

    private int journalId;

    private int shipId;

    private LocalDateTime timestamp;

    private int tosystemId;

    private BigDecimal distance;

    private BigDecimal fuelused;

    /**
     * Constructor.
     */
    public DBNavlog() {
        // Default Constructor
    }

    /**
     * Constructor.
     *
     * @param id int
     * @param journalId int
     * @param shipId int
     * @param timestamp {@link LocalDateTime}
     * @param tosystemId int
     * @param distance {@link BigDecimal}
     * @param fuelused {@link BigDecimal}
     */
    @SuppressWarnings("squid:S00107")
    public DBNavlog(final int id, final int journalId, final int shipId, final LocalDateTime timestamp,
            final int tosystemId, final BigDecimal distance, final BigDecimal fuelused) {
        super(id);
        this.journalId = journalId;
        this.shipId = shipId;
        this.timestamp = timestamp;
        this.tosystemId = tosystemId;
        this.distance = distance;
        this.fuelused = fuelused;
    }

    public final int getShipId() {
        return shipId;
    }

    public final void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public final int getTosystemId() {
        return tosystemId;
    }

    public final void setTosystemId(int tosystemId) {
        this.tosystemId = tosystemId;
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

    public final int getJournalId() {
        return journalId;
    }

    public final void setJournalId(int journalId) {
        this.journalId = journalId;
    }

    public final LocalDateTime getTimestamp() {
        return timestamp;
    }

    public final void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }


}
