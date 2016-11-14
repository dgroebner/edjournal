package de.dgroebner.edjson.db.model;


/**
 * Modellklasse für die Datenbanktabelle 'starport_visits'
 * 
 * @author dgroebner
 */
public class DBStarportVisits extends AbstractModel {

    private int journalId;

    private int starportId;

    private int shipId;

    /**
     * Constructor.
     */
    public DBStarportVisits() {
        // Default Constructor
    }

    /**
     * Constructor.
     *
     * @param id int
     * @param journalId int
     * @param starportId int
     * @param shipId int
     */
    public DBStarportVisits(final int id, final int journalId, final int starportId, final int shipId) {
        super(id);
        this.journalId = journalId;
        this.starportId = starportId;
        this.shipId = shipId;
    }

    public final int getJournalId() {
        return journalId;
    }

    public final void setJournalId(int journalId) {
        this.journalId = journalId;
    }

    public final int getStarportId() {
        return starportId;
    }

    public final void setStarportId(int starportId) {
        this.starportId = starportId;
    }

    public final int getShipId() {
        return shipId;
    }

    public final void setShipId(int shipId) {
        this.shipId = shipId;
    }

}
