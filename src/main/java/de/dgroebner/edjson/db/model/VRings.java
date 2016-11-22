package de.dgroebner.edjson.db.model;

import java.math.BigDecimal;


/**
 * Modellklasse für den View 'vrings'
 * 
 * @author dgroebner
 */
public class VRings extends AbstractModel {

    private String art;

    private String starsystemname;

    private String ringname;

    private String ringtype;

    private String starsystemUrl;

    private BigDecimal distanceInSystem;

    private BigDecimal distanceToluku;

    /**
     * Constructor.
     */
    public VRings() {
        // Default Constructor
    }

    /**
     * Constructor.
     * 
     * @param art {@link String}
     * @param starsystemname {@link String}
     * @param ringname {@link String}
     * @param ringtype {@link String}
     * @param starsystemUrl {@link String}
     * @param distanceInSystem {@link BigDecimal}
     * @param distanceToluku {@link BigDecimal}
     */
    @SuppressWarnings("squid:S00107")
    public VRings(final String art, final String starsystemname, final String ringname, final String ringtype,
            final String starsystemUrl, final BigDecimal distanceInSystem, final BigDecimal distanceToluku) {
        super(0);
        this.art = art;
        this.starsystemname = starsystemname;
        this.ringname = ringname;
        this.ringtype = ringtype;
        this.starsystemUrl = starsystemUrl;
        this.distanceInSystem = distanceInSystem;
        this.distanceToluku = distanceToluku;
    }

    public final String getArt() {
        return art;
    }

    public final void setArt(String art) {
        this.art = art;
    }

    public final String getStarsystemname() {
        return starsystemname;
    }

    public final void setStarsystemname(String starsystemname) {
        this.starsystemname = starsystemname;
    }

    public final String getRingname() {
        return ringname;
    }

    public final void setRingname(String ringname) {
        this.ringname = ringname;
    }

    public final String getRingtype() {
        return ringtype;
    }

    public final void setRingtype(String ringtype) {
        this.ringtype = ringtype;
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
