package de.dgroebner.edjson.db.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Modellklasse für die Datenbanktabelle 'properties'
 * 
 * @author dgroebner
 */
@Data
@Builder
public class DBProperties {

    @Setter
    @Getter
    private int id;

    @Setter
    @Getter
    private String entry;

    @Setter
    @Getter
    private String value;

}
