package de.dgroebner.edjson.db.model;


/**
 * Modellklasse für die Datenbanktabelle 'starport'
 * 
 * @author dgroebner
 */
public class DBStarport extends AbstractModel {

    private int journalId;

    private int starsystemId;

    private String name;

    private String type;

    private String inaraUrl;

    private int factionId;

    private String security;

    private String allegiance;

    private String government;

    private String economy;

    /**
     * Constructor.
     */
    public DBStarport() {
        // Default Constructor
    }

    /**
     * Constructor.
     *
     * @param id int
     * @param journalId int
     * @param starsystemId int
     * @param name {@link String}
     * @param type {@link String}
     * @param factionId int
     * @param allegiance {@link String}
     * @param inaraUrl {@link String}
     * @param security {@link String}
     * @param government {@link String}
     * @param economy {@link String}
     */
    @SuppressWarnings("squid:S00107")
    public DBStarport(final int id, final int journalId, final int starsystemId, final String name, final String type,
            final String inaraUrl, final int factionId, final String security, final String allegiance,
            final String government, final String economy) {
        super(id);
        this.journalId = journalId;
        this.starsystemId = starsystemId;
        this.name = name;
        this.type = type;
        this.factionId = factionId;
        this.allegiance = allegiance;
        this.inaraUrl = inaraUrl;
        this.security = security;
        this.government = government;
        this.economy = economy;
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

    public final String getInaraUrl() {
        return inaraUrl;
    }

    public final void setInaraUrl(String inaraUrl) {
        this.inaraUrl = inaraUrl;
    }

    public final int getFactionId() {
        return factionId;
    }

    public final void setFactionId(int factionId) {
        this.factionId = factionId;
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

    public final int getStarsystemId() {
        return starsystemId;
    }

    public final void setStarsystemId(int starsystemId) {
        this.starsystemId = starsystemId;
    }

    public final String getType() {
        return type;
    }

    public final void setType(String type) {
        this.type = type;
    }
}
