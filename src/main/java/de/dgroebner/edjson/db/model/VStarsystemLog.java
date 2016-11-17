package de.dgroebner.edjson.db.model;


/**
 * Modellklasse für den View 'vstarsystemslog'
 * 
 * @author dgroebner
 */
public class VStarsystemLog extends AbstractModel {

    private int visits;

    private String systemname;

    private String systemUrl;

    private String factionName;

    private String factionUrl;

    private String security;

    private String allegiance;

    private String government;

    private String economy;

    private String starpos;

    /**
     * Constructor.
     */
    public VStarsystemLog() {
        // Default Constructor
    }

    /**
     * Constructor.
     * 
     * @param visits int
     * @param systemname {@link String}
     * @param systemUrl {@link String}
     * @param factionName {@link String}
     * @param factionUrl {@link String}
     * @param allegiance {@link String}
     * @param security {@link String}
     * @param government {@link String}
     * @param economy {@link String}
     * @param starpos {@link String}
     */
    @SuppressWarnings("squid:S00107")
    public VStarsystemLog(final int visits, final String systemname, final String systemUrl, final String factionName,
            final String factionUrl, final String security, final String allegiance, final String government,
            final String economy, final String starpos) {
        super(0);
        this.visits = visits;
        this.systemUrl = systemUrl;
        this.systemname = systemname;
        this.factionName = factionName;
        this.allegiance = allegiance;
        this.factionUrl = factionUrl;
        this.security = security;
        this.government = government;
        this.economy = economy;
        this.starpos = starpos;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
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

    public final String getSecurity() {
        return security;
    }

    public final void setSecurity(String security) {
        this.security = security;
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

    public final String getStarpos() {
        return starpos;
    }

    public final void setStarpos(String starpos) {
        this.starpos = starpos;
    }
}
