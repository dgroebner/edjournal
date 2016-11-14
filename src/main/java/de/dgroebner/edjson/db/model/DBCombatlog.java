package de.dgroebner.edjson.db.model;

import java.time.LocalDateTime;


/**
 * Modellklasse für die Datenbanktabelle 'combatlog'
 * 
 * @author dgroebner
 */
public class DBCombatlog extends AbstractModel {

    private int journalId;

    private int shipId;

    private LocalDateTime timestamp;

    private String action;

    private String enemy;

    private int factionId;

    private int reward;

    /**
     * Constructor.
     */
    public DBCombatlog() {
        // Default Constructor
    }

    /**
     * Constructor.
     *
     * @param id int
     * @param journalId int
     * @param shipId int
     * @param timestamp {@link LocalDateTime}
     * @param action {@link String}
     * @param enemy {@link String}
     * @param factionId int
     * @param reward int
     */
    @SuppressWarnings("squid:S00107")
    public DBCombatlog(final int id, final int journalId, final int shipId, final LocalDateTime timestamp,
            final String action, final String enemy, final int factionId, final int reward) {
        super(id);
        this.journalId = journalId;
        this.timestamp = timestamp;
        this.shipId = shipId;
        this.timestamp = timestamp;
        this.action = action;
        this.enemy = enemy;
        this.factionId = factionId;
        this.reward = reward;
    }

    public final int getJournalId() {
        return journalId;
    }

    public final void setJournalId(int journalId) {
        this.journalId = journalId;
    }

    public final int getShipId() {
        return shipId;
    }

    public final void setShipId(int shipId) {
        this.shipId = shipId;
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

    public final int getFactionId() {
        return factionId;
    }

    public final void setFactionId(int factionId) {
        this.factionId = factionId;
    }

    public final int getReward() {
        return reward;
    }

    public final void setReward(int reward) {
        this.reward = reward;
    }

}
