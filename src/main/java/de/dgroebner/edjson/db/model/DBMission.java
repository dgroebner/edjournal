package de.dgroebner.edjson.db.model;

import java.time.LocalDateTime;


/**
 * Modellklasse für die Datenbanktabelle 'mission'
 * 
 * @author dgroebner
 */
public class DBMission extends AbstractModel {

    private int journalId;

    private int missionId;

    private int factionId;

    private String name;

    private String commodity;

    private int commodityCount;

    private String passengerType;

    private int passengerCount;

    private boolean passengerVip;

    private boolean passengerWanted;

    private String destination;

    private String destinationPort;

    private String targetFaction;

    private int reward;

    private LocalDateTime expiry;

    private String status;

    private int financeId;

    /**
     * Constructor.
     */
    public DBMission() {
        // Default Constructor
    }

    /**
     * Constructor.
     * 
     * @param id int
     * @param journalId int
     * @param missionId int
     * @param factionId int
     * @param name {@link String}
     * @param commodity {@link String}
     * @param commodityCount int
     * @param passengerType {@link String}
     * @param passengerCount int
     * @param passengerVip boolean
     * @param passengerWanted boolean
     * @param destination {@link String}
     * @param destinationPort {@link String}
     * @param targetFaction {@link String}
     * @param reward int
     * @param expiry {@link LocalDateTime}
     * @param status {@link String}
     * @param financeId int
     */
    @SuppressWarnings("squid:S00107")
    public DBMission(final int id, final int journalId, final int missionId, final int factionId, final String name,
            final String commodity, final int commodityCount, final String passengerType, final int passengerCount,
            final boolean passengerVip, final boolean passengerWanted, final String destination,
            final String destinationPort, final String targetFaction, final int reward, final LocalDateTime expiry,
            final String status, final int financeId) {
        super(id);
        this.journalId = journalId;
        this.missionId = missionId;
        this.factionId = factionId;
        this.name = name;
        this.commodity = commodity;
        this.commodityCount = commodityCount;
        this.passengerType = passengerType;
        this.passengerCount = passengerCount;
        this.passengerVip = passengerVip;
        this.passengerWanted= passengerWanted;
        this.destination = destination;
        this.destinationPort = destinationPort;
        this.targetFaction = targetFaction;
        this.reward = reward;
        this.expiry = expiry;
        this.status = status;
        this.financeId = financeId;
    }

    public final int getJournalId() {
        return journalId;
    }

    public final void setJournalId(int journalId) {
        this.journalId = journalId;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final int getMissionId() {
        return missionId;
    }

    public final void setMissionId(int missionId) {
        this.missionId = missionId;
    }

    public final int getFactionId() {
        return factionId;
    }

    public final void setFactionId(int factionId) {
        this.factionId = factionId;
    }

    public final String getCommodity() {
        return commodity;
    }

    public final void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public final int getCommodityCount() {
        return commodityCount;
    }

    public final void setCommodityCount(int commodityCount) {
        this.commodityCount = commodityCount;
    }

    public final String getPassengerType() {
        return passengerType;
    }

    public final void setPassengerType(String passengerType) {
        this.passengerType = passengerType;
    }

    public final int getPassengerCount() {
        return passengerCount;
    }

    public final void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    public final boolean isPassengerVip() {
        return passengerVip;
    }

    public final void setPassengerVip(boolean passengerVip) {
        this.passengerVip = passengerVip;
    }

    public final boolean isPassengerWanted() {
        return passengerWanted;
    }

    public final void setPassengerWanted(boolean passengerWanted) {
        this.passengerWanted = passengerWanted;
    }

    public final String getDestination() {
        return destination;
    }

    public final void setDestination(String destination) {
        this.destination = destination;
    }

    public final String getDestinationPort() {
        return destinationPort;
    }

    public final void setDestinationPort(String destinationPort) {
        this.destinationPort = destinationPort;
    }

    public final String getTargetFaction() {
        return targetFaction;
    }

    public final void setTargetFaction(String targetFaction) {
        this.targetFaction = targetFaction;
    }

    public final int getReward() {
        return reward;
    }

    public final void setReward(int reward) {
        this.reward = reward;
    }

    public final LocalDateTime getExpiry() {
        return expiry;
    }

    public final void setExpiry(LocalDateTime expiry) {
        this.expiry = expiry;
    }

    public final String getStatus() {
        return status;
    }

    public final void setStatus(String status) {
        this.status = status;
    }

    public final int getFinanceId() {
        return financeId;
    }

    public final void setFinanceId(int financeId) {
        this.financeId = financeId;
    }

}
