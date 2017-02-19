package de.dgroebner.edjson.db.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Modellklasse für die Datenbanktabelle 'combatlog'
 * 
 * @author dgroebner
 */
@Data
@Builder
public class DBCombatlog {

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
    private String action;

    @Setter
    @Getter
    private String enemy;

    @Setter
    @Getter
    private int factionId;

    @Setter
    @Getter
    private int reward;
}
