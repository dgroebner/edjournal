package de.dgroebner.edjson.db.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Modellklasse für die Datenbanktabelle 'ring'
 * 
 * @author dgroebner
 */
@Data
@Builder
public class DBRing {

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
    private int starId;

    @Setter
    @Getter
    private int planetId;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String type;

    @Setter
    @Getter
    private String massMt;

    @Setter
    @Getter
    private String innerRad;

    @Setter
    @Getter
    private String outerRad;

}
