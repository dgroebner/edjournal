package de.dgroebner.edjson.db.model;

import java.math.BigDecimal;

/**
 * Modellklasse für den View 'vstar'
 * 
 * @author dgroebner
 */
public class VStar extends AbstractModel {

    private String starname;

    private String type;

    private BigDecimal stellarMass;

    private BigDecimal radius;

    private BigDecimal absoluteMagnitude;

    private int ageMY;

    private BigDecimal surfaceTemperature;

    private BigDecimal semiMajorAxis;

    private BigDecimal eccentricity;

    private BigDecimal orbitalInclination;

    private BigDecimal periapsis;

    private BigDecimal orbitalPeriod;

    private BigDecimal rotationPeriod;

    private String starsystemUrl;

    private BigDecimal distanceInSystem;

    private BigDecimal distanceToluku;

    /**
     * Constructor.
     */
    public VStar() {
        // Default Constructor
    }

    /**
     * Constructor.
     * 
     * @param starname {@link String}
     * @param type {@link String}
     * @param stellarMass {@link BigDecimal}
     * @param radius {@link BigDecimal}
     * @param absoluteMagnitude {@link BigDecimal}
     * @param ageMY int
     * @param surfaceTemperature {@link BigDecimal}
     * @param semiMajorAxis {@link BigDecimal}
     * @param eccentricity {@link BigDecimal}
     * @param orbitalInclination {@link BigDecimal}
     * @param periapsis {@link BigDecimal}
     * @param orbitalPeriod {@link BigDecimal}
     * @param rotationPeriod {@link BigDecimal}
     * @param starsystemUrl {@link String}
     * @param distanceInSystem {@link BigDecimal}
     * @param distanceToluku {@link BigDecimal}
     */
    @SuppressWarnings("squid:S00107")
    public VStar(final String starname, final String type, final BigDecimal stellarMass, final BigDecimal radius,
            final BigDecimal absoluteMagnitude, final int ageMY, final BigDecimal surfaceTemperature,
            final BigDecimal semiMajorAxis, final BigDecimal eccentricity, final BigDecimal orbitalInclination,
            final BigDecimal periapsis, final BigDecimal orbitalPeriod, final BigDecimal rotationPeriod,
            final String starsystemUrl, final BigDecimal distanceInSystem, final BigDecimal distanceToluku) {
        super(0);
        this.starname = starname;
        this.type = type;
        this.stellarMass = stellarMass;
        this.radius = radius;
        this.absoluteMagnitude = absoluteMagnitude;
        this.ageMY = ageMY;
        this.surfaceTemperature = surfaceTemperature;
        this.semiMajorAxis = semiMajorAxis;
        this.eccentricity = eccentricity;
        this.orbitalInclination = orbitalInclination;
        this.periapsis = periapsis;
        this.orbitalPeriod = orbitalPeriod;
        this.rotationPeriod = rotationPeriod;
        this.starsystemUrl = starsystemUrl;
        this.distanceInSystem = distanceInSystem;
        this.distanceToluku = distanceToluku;
    }

    public final String getType() {
        return type;
    }

    public final void setType(String type) {
        this.type = type;
    }

    public final BigDecimal getStellarMass() {
        return stellarMass;
    }

    public final void setStellarMass(BigDecimal stellarMass) {
        this.stellarMass = stellarMass;
    }

    public final BigDecimal getRadius() {
        return radius;
    }

    public final void setRadius(BigDecimal radius) {
        this.radius = radius;
    }

    public final BigDecimal getAbsoluteMagnitude() {
        return absoluteMagnitude;
    }

    public final void setAbsoluteMagnitude(BigDecimal absoluteMagnitude) {
        this.absoluteMagnitude = absoluteMagnitude;
    }

    public final int getAgeMY() {
        return ageMY;
    }

    public final void setAgeMY(int ageMY) {
        this.ageMY = ageMY;
    }

    public final BigDecimal getSurfaceTemperature() {
        return surfaceTemperature;
    }

    public final void setSurfaceTemperature(BigDecimal surfaceTemperature) {
        this.surfaceTemperature = surfaceTemperature;
    }

    public final BigDecimal getSemiMajorAxis() {
        return semiMajorAxis;
    }

    public final void setSemiMajorAxis(BigDecimal semiMajorAxis) {
        this.semiMajorAxis = semiMajorAxis;
    }

    public final BigDecimal getEccentricity() {
        return eccentricity;
    }

    public final void setEccentricity(BigDecimal eccentricity) {
        this.eccentricity = eccentricity;
    }

    public final BigDecimal getOrbitalInclination() {
        return orbitalInclination;
    }

    public final void setOrbitalInclination(BigDecimal orbitalInclination) {
        this.orbitalInclination = orbitalInclination;
    }

    public final BigDecimal getPeriapsis() {
        return periapsis;
    }

    public final void setPeriapsis(BigDecimal periapsis) {
        this.periapsis = periapsis;
    }

    public final BigDecimal getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public final void setOrbitalPeriod(BigDecimal orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public final BigDecimal getRotationPeriod() {
        return rotationPeriod;
    }

    public final void setRotationPeriod(BigDecimal rotationPeriod) {
        this.rotationPeriod = rotationPeriod;
    }

    public String getStarsystemUrl() {
        return starsystemUrl;
    }

    public void setStarsystemUrl(String starsystemUrl) {
        this.starsystemUrl = starsystemUrl;
    }

    public final String getStarname() {
        return starname;
    }

    public final void setStarname(String starname) {
        this.starname = starname;
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
