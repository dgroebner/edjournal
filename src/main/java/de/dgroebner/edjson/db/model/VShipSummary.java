package de.dgroebner.edjson.db.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

/**
 * Modellklasse für den View 'vshipsummary'
 * 
 * @author dgroebner
 */
@Value
@Builder
public class VShipSummary {

    @Getter
    private int edId;

    @Getter
    private String type;

    @Getter
    private String callsign;

    @Getter
    private String inaraUrl;

    @Getter
    private BigDecimal distance;

}