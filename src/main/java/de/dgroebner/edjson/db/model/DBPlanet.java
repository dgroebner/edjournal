package de.dgroebner.edjson.db.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Modellklasse für die Datenbanktabelle 'planet'
 * 
 * @author dgroebner
 */
@Data
@Builder
public class DBPlanet {

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
    private boolean tidalLock;

    @Setter
    @Getter
    private String terraformState;

    @Setter
    @Getter
    private String atmosphere;

    @Setter
    @Getter
    private String volcanism;

    @Setter
    @Getter
    private BigDecimal massEM;

    @Setter
    @Getter
    private BigDecimal radius;

    @Setter
    @Getter
    private BigDecimal surfaceGravity;

    @Setter
    @Getter
    private BigDecimal surfaceTemperature;

    @Setter
    @Getter
    private BigDecimal surfacePressure;

    @Setter
    @Getter
    private boolean landable;

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
