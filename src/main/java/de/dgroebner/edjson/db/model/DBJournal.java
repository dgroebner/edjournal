package de.dgroebner.edjson.db.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Modellklasse für die Datenbanktabelle 'journal'
 * 
 * @author dgroebner
 */
@Data
@Builder
public class DBJournal {

    @Setter
    @Getter
    private int id;

    @Setter
    @Getter
    private int journalfileId;

    @Setter
    @Getter
    @NonNull
    private LocalDateTime timestamp;

    @Setter
    @Getter
    @NonNull
    private String event;

    @Setter
    @Getter
    @NonNull
    private String message;
}
