package de.dgroebner.edjson.db.model;


/**
 * Modellklasse für die Datenbanktabelle 'faction'
 * 
 * @author dgroebner
 */
public class DBFaction extends AbstractModel {

    private int journalId;

    private String name;

    private String state;

    private String allegiance;

    private String inaraUrl;

    /**
     * Constructor.
     */
    public DBFaction() {
        // Default Constructor
    }

    /**
     * Constructor.
     *
     * @param id int
     * @param journalId int
     * @param name {@link String}
     * @param state {@link String}
     * @param allegiance {@link String}
     * @param inaraUrl {@link String}
     */
    public DBFaction(final int id, final int journalId, final String name, final String state,
            final String allegiance, final String inaraUrl) {
        super(id);
        this.journalId = journalId;
        this.name = name;
        this.state = state;
        this.allegiance = allegiance;
        this.inaraUrl = inaraUrl;
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

    public final String getState() {
        return state;
    }

    public final void setState(String state) {
        this.state = state;
    }

    public final String getAllegiance() {
        return allegiance;
    }

    public final void setAllegiance(String allegiance) {
        this.allegiance = allegiance;
    }

    public final String getInaraUrl() {
        return inaraUrl;
    }

    public final void setInaraUrl(String inaraUrl) {
        this.inaraUrl = inaraUrl;
    }

}
