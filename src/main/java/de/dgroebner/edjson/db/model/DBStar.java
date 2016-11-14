package de.dgroebner.edjson.db.model;

import java.math.BigDecimal;


/**
 * Modellklasse für die Datenbanktabelle 'star'
 * 
 * @author dgroebner
 */
public class DBStar extends AbstractModel {

    private int journalId;

    private int starsystemId;

    private String name;

    private String type;

    private BigDecimal distanceFromArrivalLS;

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

    /**
     * Constructor.
     */
    public DBStar() {
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
     * @param distanceFromArrivalLS {@link BigDecimal}
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
     */
    @SuppressWarnings("squid:S00107")
    public DBStar(final int id, final int journalId, final int starsystemId, final String name, final String type,
            final BigDecimal distanceFromArrivalLS, final BigDecimal stellarMass, final BigDecimal radius,
            final BigDecimal absoluteMagnitude, final int ageMY, final BigDecimal surfaceTemperature,
            final BigDecimal semiMajorAxis, final BigDecimal eccentricity, final BigDecimal orbitalInclination,
            final BigDecimal periapsis, final BigDecimal orbitalPeriod, final BigDecimal rotationPeriod) {
        super(id);
        this.journalId = journalId;
        this.starsystemId = starsystemId;
        this.name = name;
        this.type = type;
        this.distanceFromArrivalLS = distanceFromArrivalLS;
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

    public final BigDecimal getDistanceFromArrivalLS() {
        return distanceFromArrivalLS;
    }

    public final void setDistanceFromArrivalLS(BigDecimal distanceFromArrivalLS) {
        this.distanceFromArrivalLS = distanceFromArrivalLS;
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

}
