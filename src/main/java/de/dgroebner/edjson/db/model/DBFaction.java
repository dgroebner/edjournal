package de.dgroebner.edjson.db.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * Modellklasse für die Datenbanktabelle 'faction'
 * 
 * @author dgroebner
 */
@Data
@Builder
public class DBFaction {

    @Setter
    @Getter
    private int id;

    @Setter
    @Getter
    private int journalId;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String state;

    @Setter
    @Getter
    private String allegiance;

    @Setter
    @Getter
    private String inaraUrl;

}
