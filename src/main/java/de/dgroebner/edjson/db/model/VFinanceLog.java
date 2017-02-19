package de.dgroebner.edjson.db.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

/**
 * Modellklasse für den View 'vfinancelog'
 * 
 * @author dgroebner
 */
@Value
@Builder
public class VFinanceLog {

    @Getter
    private LocalDateTime valutadatum;

    @Getter
    private int amount;

    @Getter
    private String category;

    @Getter
    private String remark;

    @Getter
    private String factionName;

    @Getter
    private String factionUrl;

}
