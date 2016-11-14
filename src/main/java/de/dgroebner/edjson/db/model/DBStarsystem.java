package de.dgroebner.edjson.db.model;


/**
 * Modellklasse für die Datenbanktabelle 'starsystem'
 * 
 * @author dgroebner
 */
public class DBStarsystem extends AbstractModel {

    private int journalId;

    private String name;

    private String inaraUrl;

    private int factionId;

    private String security;

    private String allegiance;

    private String government;

    private String economy;

    private String starpos;

    /**
     * Constructor.
     */
    public DBStarsystem() {
        // Default Constructor
    }

    /**
     * Constructor.
     *
     * @param id int
     * @param journalId int
     * @param name {@link String}
     * @param factionId int
     * @param allegiance {@link String}
     * @param inaraUrl {@link String}
     * @param security {@link String}
     * @param government {@link String}
     * @param economy {@link String}
     * @param starpos {@link String}
     */
    @SuppressWarnings("squid:S00107")
    public DBStarsystem(final int id, final int journalId, final String name, final String inaraUrl,
            final int factionId, final String security, final String allegiance, final String government,
            final String economy, final String starpos) {
        super(id);
        this.journalId = journalId;
        this.name = name;
        this.factionId = factionId;
        this.allegiance = allegiance;
        this.inaraUrl = inaraUrl;
        this.security = security;
        this.government = government;
        this.economy = economy;
        this.starpos = starpos;
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

    public final String getStarpos() {
        return starpos;
    }

    public final void setStarpos(String starpos) {
        this.starpos = starpos;
    }
}
