package de.dgroebner.edjson.db.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;


/**
 * Modellklasse für des View 'vmissionlog'
 * 
 * @author dgroebner
 */
@Value
@Builder
public class VMissionLog {

    @Getter
    private String factionname;

    @Getter
    private String missionname;

    @Getter
    private String commodity;

    @Getter
    private int commodityCount;

    @Getter
    private String passengerType;

    @Getter
    private int passengerCount;

    @Getter
    private boolean passengerVip;

    @Getter
    private boolean passengerWanted;

    @Getter
    private String destination;

    @Getter
    private String destinationPort;

    @Getter
    private String status;

    @Getter
    private int reward;

}
