package de.dgroebner.edjson.db.model;



/**
 * Modellklasse für die Datenbanktabelle 'ring'
 * 
 * @author dgroebner
 */
public class DBRing extends AbstractModel {

    private int journalId;

    private int starsystemId;

    private int starId;

    private int planetId;

    private String name;

    private String type;

    private String massMt;

    private String innerRad;

    private String outerRad;

    /**
     * Constructor.
     */
    public DBRing() {
        // Default Constructor
    }

    /**
     * Constructor.
     *
     * @param id int
     * @param journalId int
     * @param starsystemId int
     * @param starId int
     * @param planetId int
     * @param name {@link String}
     * @param type {@link String}
     * @param massMt {@link String}
     * @param innerRad {@link String}
     * @param outerRad {@link String}
     */
    @SuppressWarnings("squid:S00107")
    public DBRing(final int id, final int journalId, final int starsystemId, final int starId, final int planetId,
            final String name, final String type, final String massMt, final String innerRad, final String outerRad) {
        super(id);
        this.journalId = journalId;
        this.starsystemId = starsystemId;
        this.starId = starId;
        this.planetId = planetId;
        this.name = name;
        this.type = type;
        this.massMt = massMt;
        this.innerRad = innerRad;
        this.outerRad = outerRad;
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

    public final int getStarId() {
        return starId;
    }

    public final void setStarId(int starId) {
        this.starId = starId;
    }

    public final int getPlanetId() {
        return planetId;
    }

    public final void setPlanetId(int planetId) {
        this.planetId = planetId;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final String getType() {
        return type;
    }

    public final void setType(String type) {
        this.type = type;
    }

    public final String getMassMt() {
        return massMt;
    }

    public final void setMassMt(String massMt) {
        this.massMt = massMt;
    }

    public final String getInnerRad() {
        return innerRad;
    }

    public final void setInnerRad(String innerRad) {
        this.innerRad = innerRad;
    }

    public final String getOuterRad() {
        return outerRad;
    }

    public final void setOuterRad(String outerRad) {
        this.outerRad = outerRad;
    }

}
