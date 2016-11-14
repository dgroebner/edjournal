package de.dgroebner.edjson.db.model;

import java.math.BigDecimal;


/**
 * Modellklasse für die Datenbanktabelle 'planet_material'
 * 
 * @author dgroebner
 */
public class DBPlanetMaterial extends AbstractModel {

    private int journalId;

    private int starsystemId;

    private int planetId;

    private int materialId;

    private BigDecimal amount;

    /**
     * Constructor.
     */
    public DBPlanetMaterial() {
        // Default Constructor
    }

    /**
     * Constructor.
     *
     * @param id int
     * @param journalId int
     * @param planetId int
     * @param materialId int
     * @param amount {@link BigDecimal}
     */
    @SuppressWarnings("squid:S00107")
    public DBPlanetMaterial(final int id, final int journalId, final int planetId, final int materialId,
            final BigDecimal amount) {
        super(id);
        this.journalId = journalId;
        this.materialId = materialId;
        this.planetId = planetId;
        this.amount = amount;
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

    public final int getPlanetId() {
        return planetId;
    }

    public final void setPlanetId(int planetId) {
        this.planetId = planetId;
    }

    public final int getMaterialId() {
        return materialId;
    }

    public final void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public final BigDecimal getAmount() {
        return amount;
    }

    public final void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
