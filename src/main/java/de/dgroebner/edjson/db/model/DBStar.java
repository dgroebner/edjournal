package de.dgroebner.edjson.db.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Modellklasse für die Datenbanktabelle 'star'
 * 
 * @author dgroebner
 */
@Data
@Builder
public class DBStar {

    @Setter
    @Getter
    private int id;

    @Setter
    @Getter
    private int journalId;

    @Setter
    @Getter
    private int starsystemId;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String type;

    @Setter
    @Getter
    private BigDecimal distanceFromArrivalLS;

    @Setter
    @Getter
    private BigDecimal stellarMass;

    @Setter
    @Getter
    private BigDecimal radius;

    @Setter
    @Getter
    private BigDecimal absoluteMagnitude;

    @Setter
    @Getter
    private int ageMY;

    @Setter
    @Getter
    private BigDecimal surfaceTemperature;

    @Setter
    @Getter
    private BigDecimal semiMajorAxis;

    @Setter
    @Getter
    private BigDecimal eccentricity;

    @Setter
    @Getter
    private BigDecimal orbitalInclination;

    @Setter
    @Getter
    private BigDecimal periapsis;

    @Setter
    @Getter
    private BigDecimal orbitalPeriod;

    @Setter
    @Getter
    private BigDecimal rotationPeriod;

}
