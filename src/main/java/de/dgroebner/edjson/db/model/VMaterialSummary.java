package de.dgroebner.edjson.db.model;

import java.math.BigDecimal;

/**
 * Modellklasse für den View 'vmaterial_summary'
 * 
 * @author dgroebner
 */
public class VMaterialSummary extends AbstractModel {

    private String material;

    private BigDecimal amount;

    private String planet;

    private String planetType;

    private BigDecimal gravity;

    private String materialUrl;

    private String starsystemUrl;

    private BigDecimal distanceInSystem;

    private BigDecimal distanceToluku;

    /**
     * Constructor.
     */
    public VMaterialSummary() {
        // Default Constructor
    }

    /**
     * Constructor.
     * 
     * @param material {@link String}
     * @param amount {@link BigDecimal}
     * @param planet {@link String}
     * @param planetType {@link String}
     * @param gravity {@link BigDecimal}
     * @param materialUrl {@link String}
     * @param starsystemUrl {@link String}
     * @param distanceInSystem {@link BigDecimal}
     * @param distanceToluku {@link BigDecimal}
     */
    @SuppressWarnings("squid:S00107")
    public VMaterialSummary(final String material, final BigDecimal amount, final String planet,
            final String planetType, final BigDecimal gravity, final String materialUrl, final String starsystemUrl,
            final BigDecimal distanceInSystem, final BigDecimal distanceToluku) {
        super(0);
        this.material = material;
        this.amount = amount;
        this.planet = planet;
        this.planetType = planetType;
        this.gravity = gravity;
        this.materialUrl = materialUrl;
        this.starsystemUrl = starsystemUrl;
        this.distanceInSystem = distanceInSystem;
        this.distanceToluku = distanceToluku;
    }

    public final String getMaterial() {
        return material;
    }

    public final void setMaterial(String material) {
        this.material = material;
    }

    public final BigDecimal getAmount() {
        return amount;
    }

    public final void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public final String getPlanet() {
        return planet;
    }

    public final void setPlanet(String planet) {
        this.planet = planet;
    }

    public final String getPlanetType() {
        return planetType;
    }

    public final void setPlanetType(String planetType) {
        this.planetType = planetType;
    }

    public final BigDecimal getGravity() {
        return gravity;
    }

    public final void setGravity(BigDecimal gravity) {
        this.gravity = gravity;
    }

    public final String getMaterialUrl() {
        return materialUrl;
    }

    public final void setMaterialUrl(String materialUrl) {
        this.materialUrl = materialUrl;
    }

    public final String getStarsystemUrl() {
        return starsystemUrl;
    }

    public final void setStarsystemUrl(String starsystemUrl) {
        this.starsystemUrl = starsystemUrl;
    }

    public final BigDecimal getDistanceInSystem() {
        return distanceInSystem;
    }

    public final void setDistanceInSystem(BigDecimal distanceInSystem) {
        this.distanceInSystem = distanceInSystem;
    }

    public final BigDecimal getDistanceToluku() {
        return distanceToluku;
    }

    public final void setDistanceToluku(BigDecimal distanceToluku) {
        this.distanceToluku = distanceToluku;
    }

}
