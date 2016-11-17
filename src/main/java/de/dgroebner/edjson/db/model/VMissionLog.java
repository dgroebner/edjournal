package de.dgroebner.edjson.db.model;


/**
 * Modellklasse für des View 'vmissionlog'
 * 
 * @author dgroebner
 */
public class VMissionLog extends AbstractModel {

    private String factionname;

    private String missionname;

    private String commodity;

    private int commodityCount;

    private String passengerType;

    private int passengerCount;

    private boolean passengerVip;

    private boolean passengerWanted;

    private String destination;

    private String destinationPort;

    private String status;

    private int reward;

    /**
     * Constructor.
     */
    public VMissionLog() {
        // Default Constructor
    }

    /**
     * Constructor.
     * 
     * @param factionname {@link String}
     * @param missionname {@link String}
     * @param commodity {@link String}
     * @param commodityCount int
     * @param passengerType {@link String}
     * @param passengerCount int
     * @param passengerVip boolean
     * @param passengerWanted boolean
     * @param destination {@link String}
     * @param destinationPort {@link String}
     * @param status {@link String}
     * @param reward int
     */
    @SuppressWarnings("squid:S00107")
    public VMissionLog(final String factionname, final String missionname, final String commodity,
            final int commodityCount, final String passengerType, final int passengerCount, final boolean passengerVip,
            final boolean passengerWanted, final String destination, final String destinationPort, final String status,
            final int reward) {
        super(0);
        this.factionname = factionname;
        this.missionname = missionname;
        this.commodity = commodity;
        this.commodityCount = commodityCount;
        this.passengerType = passengerType;
        this.passengerCount = passengerCount;
        this.passengerVip = passengerVip;
        this.passengerWanted = passengerWanted;
        this.destination = destination;
        this.destinationPort = destinationPort;
        this.status = status;
        this.reward = reward;
        this.status = status;
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

    public final int getReward() {
        return reward;
    }

    public final void setReward(int reward) {
        this.reward = reward;
    }

    public final String getStatus() {
        return status;
    }

    public final void setStatus(String status) {
        this.status = status;
    }

    public final String getFactionname() {
        return factionname;
    }

    public final void setFactionname(String factionname) {
        this.factionname = factionname;
    }

    public final String getMissionname() {
        return missionname;
    }

    public final void setMissionname(String missionname) {
        this.missionname = missionname;
    }
}
