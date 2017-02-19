package de.dgroebner.edjson.db.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * Modellklasse für die Datenbanktabelle 'material'
 * 
 * @author dgroebner
 */
@Data
@Builder
public class DBMaterial {

    @Setter
    @Getter
    private int id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String inaraUrl;

    @Setter
    @Getter
    private String kuerzel;

    @Setter
    @Getter
    private String edName;

    @Setter
    @Getter
    private String category;

    @Setter
    @Getter
    private int stock;

    @Setter
    @Getter
    private String rarity;

}
