package de.dgroebner.edjson.db.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Modellklasse für die Datenbanktabelle 'ship'
 * 
 * @author dgroebner
 */
@Data
@Builder
public class DBShip {

    @Setter
    @Getter
    private int id;

    @Setter
    @Getter
    private int edId;

    @Setter
    @Getter
    private String type;

    @Setter
    @Getter
    private String callsign;

    @Setter
    @Getter
    private String inaraUrl;

}
