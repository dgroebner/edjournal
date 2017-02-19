package de.dgroebner.edjson.db.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

/**
 * Modellklasse für den View 'vmaterial_summary'
 * 
 * @author dgroebner
 */
@Value
@Builder
public class VMaterialSummary {

    @Getter
    private String material;

    @Getter
    private BigDecimal amount;

    @Getter
    private String planet;

    @Getter
    private String planetType;

    @Getter
    private BigDecimal gravity;

    @Getter
    private String materialUrl;

    @Getter
    private String starsystemUrl;

    @Getter
    private BigDecimal distanceInSystem;

    @Getter
    private BigDecimal distanceToluku;

}
