package de.dgroebner.edjson.db.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

/**
 * Modellklasse für den View 'vnavlog'
 * 
 * @author dgroebner
 */
@Value
@Builder
public class VNavlog {

    @Getter
    private String shipname;

    @Getter
    private String shiptype;

    @Getter
    private String shipUrl;

    @Getter
    private LocalDateTime timestamp;

    @Getter
    private String systemname;

    @Getter
    private String systemUrl;

    @Getter
    private BigDecimal distance;

    @Getter
    private BigDecimal fuelused;

    @Getter
    private BigDecimal distanceToluku;

}
