package de.dgroebner.edjson.db.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * Modellklasse für die Datenbanktabelle 'starport_visits'
 * 
 * @author dgroebner
 */
@Data
@Builder
public class DBStarportVisits {

    @Setter
    @Getter
    private int id;

    @Setter
    @Getter
    private int journalId;

    @Setter
    @Getter
    private int starportId;

    @Setter
    @Getter
    private int shipId;

}
