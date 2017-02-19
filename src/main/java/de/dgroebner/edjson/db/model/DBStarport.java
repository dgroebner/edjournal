package de.dgroebner.edjson.db.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Modellklasse für die Datenbanktabelle 'starport'
 * 
 * @author dgroebner
 */
@Data
@Builder
public class DBStarport {

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
    private String inaraUrl;

    @Setter
    @Getter
    private int factionId;

    @Setter
    @Getter
    private String allegiance;

    @Setter
    @Getter
    private String government;

    @Setter
    @Getter
    private String economy;

}
