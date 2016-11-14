package de.dgroebner.edjson.db.model;

import java.math.BigDecimal;


/**
 * Modellklasse für die Datenbanktabelle 'planet'
 * 
 * @author dgroebner
 */
public class DBPlanet extends AbstractModel {

    private int journalId;

    private int starsystemId;

    private String name;

    private String type;

    private BigDecimal distanceFromArrivalLS;

    private boolean tidalLock;

    private String terraformState;

    private String atmosphere;

    private String volcanism;

    private BigDecimal massEM;

    private BigDecimal radius;

    private BigDecimal surfaceGravity;

    private BigDecimal surfaceTemperature;

    private BigDecimal surfacePressure;

    private boolean landable;

    private BigDecimal semiMajorAxis;

    private BigDecimal eccentricity;

    private BigDecimal orbitalInclination;

    private BigDecimal periapsis;

    private BigDecimal orbitalPeriod;

    private BigDecimal rotationPeriod;

    /**
     * Constructor.
     */
    public DBPlanet() {
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
     * @param tidalLock boolean
     * @param terraformState {@link String}
     * @param atmosphere {@link String}
     * @param volcanism {@link String}
     * @param massEM {@link BigDecimal}
     * @param radius {@link BigDecimal}
     * @param surfaceGravity {@link BigDecimal}
     * @param surfaceTemperature {@link BigDecimal}
     * @param surfacePressure {@link BigDecimal}
     * @param landable boolean
     * @param semiMajorAxis {@link BigDecimal}
     * @param eccentricity {@link BigDecimal}
     * @param orbitalInclination {@link BigDecimal}
     * @param periapsis {@link BigDecimal}
     * @param orbitalPeriod {@link BigDecimal}
     * @param rotationPeriod {@link BigDecimal}
     */
    @SuppressWarnings("squid:S00107")
    public DBPlanet(final int id, final int journalId, final int starsystemId, final String name, final String type,
            final BigDecimal distanceFromArrivalLS, final boolean tidalLock, final String terraformState,
            final String atmosphere, final String volcanism, final BigDecimal massEM, final BigDecimal radius,
            final BigDecimal surfaceGravity, final BigDecimal surfaceTemperature, final BigDecimal surfacePressure,
            final boolean landable, final BigDecimal semiMajorAxis, final BigDecimal eccentricity,
            final BigDecimal orbitalInclination, final BigDecimal periapsis, final BigDecimal orbitalPeriod,
            final BigDecimal rotationPeriod) {
        super(id);
        this.journalId = journalId;
        this.starsystemId = starsystemId;
        this.name = name;
        this.type = type;
        this.distanceFromArrivalLS = distanceFromArrivalLS;
        this.tidalLock = tidalLock;
        this.radius = radius;
        this.terraformState = terraformState;
        this.atmosphere = atmosphere;
        this.volcanism = volcanism;
        this.surfaceGravity = surfaceGravity;
        this.massEM = massEM;
        this.landable = landable;
        this.surfaceTemperature = surfaceTemperature;
        this.surfacePressure = surfacePressure;
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

    public final BigDecimal getRadius() {
        return radius;
    }

    public final void setRadius(BigDecimal radius) {
        this.radius = radius;
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

    public final boolean isTidalLock() {
        return tidalLock;
    }

    public final void setTidalLock(boolean tidalLock) {
        this.tidalLock = tidalLock;
    }

    public final String getTerraformState() {
        return terraformState;
    }

    public final void setTerraformState(String terraformState) {
        this.terraformState = terraformState;
    }

    public final String getAtmosphere() {
        return atmosphere;
    }

    public final void setAtmosphere(String atmosphere) {
        this.atmosphere = atmosphere;
    }

    public final String getVolcanism() {
        return volcanism;
    }

    public final void setVolcanism(String volcanism) {
        this.volcanism = volcanism;
    }

    public final BigDecimal getMassEM() {
        return massEM;
    }

    public final void setMassEM(BigDecimal massEM) {
        this.massEM = massEM;
    }

    public final BigDecimal getSurfaceGravity() {
        return surfaceGravity;
    }

    public final void setSurfaceGravity(BigDecimal surfaceGravity) {
        this.surfaceGravity = surfaceGravity;
    }

    public final BigDecimal getSurfacePressure() {
        return surfacePressure;
    }

    public final void setSurfacePressure(BigDecimal surfacePressure) {
        this.surfacePressure = surfacePressure;
    }

    public final boolean isLandable() {
        return landable;
    }

    public final void setLandable(boolean landable) {
        this.landable = landable;
    }

}
