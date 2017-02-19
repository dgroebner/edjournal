package de.dgroebner.edjson.db.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

/**
 * Modellklasse für den View 'vplanet'
 * 
 * @author dgroebner
 */
@Value
@Builder
public class VPlanet {

    @Getter
    private String planetname;

    @Getter
    private String type;

    @Getter
    private boolean tidalLock;

    @Getter
    private String terraformState;

    @Getter
    private String atmosphere;

    @Getter
    private String volcanism;

    @Getter
    private BigDecimal massEM;

    @Getter
    private BigDecimal radius;

    @Getter
    private BigDecimal surfaceGravity;

    @Getter
    private BigDecimal surfaceTemperature;

    @Getter
    private BigDecimal surfacePressure;

    @Getter
    private boolean landable;

    @Getter
    private BigDecimal semiMajorAxis;

    @Getter
    private BigDecimal eccentricity;

    @Getter
    private BigDecimal orbitalInclination;

    @Getter
    private BigDecimal periapsis;

    @Getter
    private BigDecimal orbitalPeriod;

    @Getter
    private BigDecimal rotationPeriod;

    @Getter
    private String starsystemUrl;

    @Getter
    private BigDecimal distanceInSystem;

    @Getter
    private BigDecimal distanceToluku;

}
