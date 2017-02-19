package de.dgroebner.edjson.db.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Modellklasse für die Datenbanktabelle 'mission'
 * 
 * @author dgroebner
 */
@Data
@Builder
public class DBMission {

    @Setter
    @Getter
    private int id;

    @Setter
    @Getter
    private int journalId;

    @Setter
    @Getter
    private int missionId;

    @Setter
    @Getter
    private int factionId;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String commodity;

    @Setter
    @Getter
    private int commodityCount;

    @Setter
    @Getter
    private String passengerType;

    @Setter
    @Getter
    private int passengerCount;

    @Setter
    @Getter
    private boolean passengerVip;

    @Setter
    @Getter
    private boolean passengerWanted;

    @Setter
    @Getter
    private String destination;

    @Setter
    @Getter
    private String destinationPort;

    @Setter
    @Getter
    private String targetFaction;

    @Setter
    @Getter
    private int reward;

    @Setter
    @Getter
    private LocalDateTime expiry;

    @Setter
    @Getter
    private String status;

    @Setter
    @Getter
    private int financeId;

}
