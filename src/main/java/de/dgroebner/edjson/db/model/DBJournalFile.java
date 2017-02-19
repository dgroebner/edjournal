package de.dgroebner.edjson.db.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Modellklasse für die Datenbanktabelle 'journalfile'
 * 
 * @author dgroebner
 */
@Data
@Builder
public class DBJournalFile {

    @Setter
    @Getter
    private int id;

    @Setter
    @Getter
    private String filename;

    @Setter
    @Getter
    private LocalDateTime readDate;

}
