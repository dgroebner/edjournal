package de.dgroebner.edjson.db.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

/**
 * Modellklasse für den View 'vstarsystemslog'
 * 
 * @author dgroebner
 */
@Value
@Builder
public class VStarsystemLog {

    @Getter
    private int visits;

    @Getter
    private String systemname;

    @Getter
    private String systemUrl;

    @Getter
    private String factionName;

    @Getter
    private String factionUrl;

    @Getter
    private String security;

    @Getter
    private String allegiance;

    @Getter
    private String government;

    @Getter
    private String economy;

    @Getter
    private String starpos;

}
