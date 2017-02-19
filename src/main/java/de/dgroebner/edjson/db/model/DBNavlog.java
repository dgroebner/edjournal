package de.dgroebner.edjson.db.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * Modellklasse für die Datenbanktabelle 'navlog'
 * 
 * @author dgroebner
 */
@Data
@Builder
public class DBNavlog {

    @Setter
    @Getter
    private int id;

    @Setter
    @Getter
    private int journalId;

    @Setter
    @Getter
    private int shipId;

    @Setter
    @Getter
    private LocalDateTime timestamp;

    @Setter
    @Getter
    private int tosystemId;

    @Setter
    @Getter
    private BigDecimal distance;

    @Setter
    @Getter
    private BigDecimal fuelused;

}
