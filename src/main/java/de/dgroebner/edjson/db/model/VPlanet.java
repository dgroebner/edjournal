package de.dgroebner.edjson.db.model;

import java.math.BigDecimal;

/**
 * Modellklasse für den View 'vplanet'
 * 
 * @author dgroebner
 */
public class VPlanet extends AbstractModel {

    private String planetname;

    private String type;

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

    private String starsystemUrl;

    private BigDecimal distanceInSystem;

    private BigDecimal distanceToluku;

    /**
     * Constructor.
     */
    public VPlanet() {
        // Default Constructor
    }

    /**
     * Constructor.
     * 
     * @param planetname {@link String}
     * @param type {@link String}
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
     * @param starsystemUrl {@link String}
     * @param distanceInSystem {@link BigDecimal}
     * @param distanceToluku {@link BigDecimal}
     */
    @SuppressWarnings("squid:S00107")
    public VPlanet(final String planetname, final String type, final boolean tidalLock, final String terraformState,
            final String atmosphere, final String volcanism, final BigDecimal massEM, final BigDecimal radius,
            final BigDecimal surfaceGravity, final BigDecimal surfaceTemperature, final BigDecimal surfacePressure,
            final boolean landable, final BigDecimal semiMajorAxis, final BigDecimal eccentricity,
            final BigDecimal orbitalInclination, final BigDecimal periapsis, final BigDecimal orbitalPeriod,
            final BigDecimal rotationPeriod, final String starsystemUrl, final BigDecimal distanceInSystem,
            final BigDecimal distanceToluku) {
        super(0);
        this.planetname = planetname;
        this.type = type;
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

    public final String getPlanetname() {
        return planetname;
    }

    public final void setPlanetname(String planetname) {
        this.planetname = planetname;
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
