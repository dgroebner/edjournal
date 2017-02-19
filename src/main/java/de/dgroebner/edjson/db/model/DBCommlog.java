package de.dgroebner.edjson.db.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * Modellklasse für die Datenbanktabelle 'commlog'
 * 
 * @author dgroebner
 */
@Data
@Builder
public class DBCommlog {

    @Setter
    @Getter
    private int id;

    @Setter
    @Getter
    private int journalId;

    @Setter
    @Getter
    private LocalDateTime timestamp;

    @Setter
    @Getter
    private String sender;

    @Setter
    @Getter
    private String reciever;

    @Setter
    @Getter
    private String channel;

    @Setter
    @Getter
    private String message;
}
