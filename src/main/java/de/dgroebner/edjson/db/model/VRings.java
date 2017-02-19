package de.dgroebner.edjson.db.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;


/**
 * Modellklasse für den View 'vrings'
 * 
 * @author dgroebner
 */
@Value
@Builder
public class VRings {

    @Getter
    private String art;

    @Getter
    private String starsystemname;

    @Getter
    private String ringname;

    @Getter
    private String ringtype;

    @Getter
    private String starsystemUrl;

    @Getter
    private BigDecimal distanceInSystem;

    @Getter
    private BigDecimal distanceToluku;

}
