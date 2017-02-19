package de.dgroebner.edjson.db.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Modellklasse für die Datenbanktabelle 'fincancedata'
 * 
 * @author dgroebner
 */
@Data
@Builder
public class DBFinancedata {

    @Setter
    @Getter
    private int id;

    @Setter
    @Getter
    private int journalId;

    @Setter
    @Getter
    private LocalDateTime valutadatum;

    @Setter
    @Getter
    private int amount;

    @Setter
    @Getter
    private String category;

    @Setter
    @Getter
    private String remark;

    @Setter
    @Getter
    private int factionId;

}
