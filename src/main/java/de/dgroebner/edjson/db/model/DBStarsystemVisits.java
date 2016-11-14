package de.dgroebner.edjson.db.model;


/**
 * Modellklasse für die Datenbanktabelle 'starsystem_visits'
 * 
 * @author dgroebner
 */
public class DBStarsystemVisits extends AbstractModel {

    private int journalId;

    private int starsystemId;

    private int shipId;

    /**
     * Constructor.
     */
    public DBStarsystemVisits() {
        // Default Constructor
    }

    /**
     * Constructor.
     *
     * @param id int
     * @param journalId int
     * @param starsystemId int
     * @param shipId int
     */
    public DBStarsystemVisits(final int id, final int journalId, final int starsystemId, final int shipId) {
        super(id);
        this.journalId = journalId;
        this.starsystemId = starsystemId;
        this.shipId = shipId;
    }

    public final int getJournalId() {
        return journalId;
    }

    public final void setJournalId(int journalId) {
        this.journalId = journalId;
    }

    public final int getStarsystemId() {
        return starsystemId;
    }

    public final void setStarsystemId(int starsystemId) {
        this.starsystemId = starsystemId;
    }

    public final int getShipId() {
        return shipId;
    }

    public final void setShipId(int shipId) {
        this.shipId = shipId;
    }

}
