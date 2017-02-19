package de.dgroebner.edjson.db.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

/**
 * Modellklasse für den View 'vstarportlog'
 * 
 * @author dgroebner
 */
@Value
@Builder
public class VStarportLog {

    @Getter
    private LocalDateTime timestamp;

    @Getter
    private String portname;

    @Getter
    private String portUrl;

    @Getter
    private String systemname;

    @Getter
    private String systemUrl;

    @Getter
    private String factionName;

    @Getter
    private String factionUrl;

    @Getter
    private String type;

    @Getter
    private String allegiance;

    @Getter
    private String government;

    @Getter
    private String economy;

    @Getter
    private BigDecimal distanceToluku;

}
