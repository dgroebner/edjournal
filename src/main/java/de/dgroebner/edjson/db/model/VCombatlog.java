package de.dgroebner.edjson.db.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;


/**
 * Modellklasse für den View 'vcombatlog'
 * 
 * @author dgroebner
 */
@Value
@Builder
public class VCombatlog {

    @Getter
    private LocalDateTime timestamp;

    @Getter
    private String action;

    @Getter
    private String enemy;

    @Getter
    private String factionName;

    @Getter
    private String factionUrl;

    @Getter
    private String shipName;

    @Getter
    private String shipUrl;

}
