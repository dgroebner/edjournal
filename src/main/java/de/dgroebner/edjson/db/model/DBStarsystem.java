package de.dgroebner.edjson.db.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * Modellklasse für die Datenbanktabelle 'starsystem'
 * 
 * @author dgroebner
 */
@Data
@Builder
public class DBStarsystem {

    @Setter
    @Getter
    private int id;

    @Setter
    @Getter
    private int journalId;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String inaraUrl;

    @Setter
    @Getter
    private int factionId;

    @Setter
    @Getter
    private String security;

    @Setter
    @Getter
    private String allegiance;

    @Setter
    @Getter
    private String government;

    @Setter
    @Getter
    private String economy;

    @Setter
    @Getter
    private String starpos;

}
