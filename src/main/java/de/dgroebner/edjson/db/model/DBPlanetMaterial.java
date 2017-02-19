package de.dgroebner.edjson.db.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Modellklasse für die Datenbanktabelle 'planet_material'
 * 
 * @author dgroebner
 */
@Data
@Builder
public class DBPlanetMaterial {

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
    private int planetId;

    @Setter
    @Getter
    private int materialId;

    @Setter
    @Getter
    private BigDecimal amount;

}
