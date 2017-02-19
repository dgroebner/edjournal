package de.dgroebner.edjson.db.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

/**
 * Modellklasse für den View 'vstar'
 * 
 * @author dgroebner
 */
@Value
@Builder
public class VStar {

    @Getter
    private String starname;

    @Getter
    private String type;

    @Getter
    private BigDecimal stellarMass;

    @Getter
    private BigDecimal radius;

    @Getter
    private BigDecimal absoluteMagnitude;

    @Getter
    private int ageMY;

    @Getter
    private BigDecimal surfaceTemperature;

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
