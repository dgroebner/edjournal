package de.dgroebner.edjson.model.data;

import java.util.Arrays;

/**
 * Enum der CQC-Ränge von Elite Dangerous
 * 
 * @author dgroebner
 */
public enum RankCQC {
    
    /* @formatter:off */
    HELPLESS("Helpless", 0),
    MOSTLY_HELPLESS("Mostly Helpless", 1),
    AMATEUR("Amateur", 2),
    SEMI_PROFESSIONAL("Semi Professional", 3),
    PROFESSIONAL("Professional", 4),
    CHAMPION("Champion", 5),
    HERO("Hero", 6),
    LEGEND("Legend", 7),
    ELITE("Elite", 8);
    /* @formatter:on */
    
    private final int code;
    
    private final String name;
    
    RankCQC(final String name, final int code) {
        this.name = name;
        this.code = code;
    }

    public final int getCode() {
        return code;
    }

    public final String getName() {
        return name;
    }

    /**
     * Liefert den {@link RankCQC} für den übergebenen code
     * 
     * @param codeForSearch int
     * @return {@link RankCQC}
     * @throws IllegalArgumentException wenn der code nicht zugeordnet werden konnte
     */
    public static RankCQC forCode(final int codeForSearch) {
        return Arrays.asList(RankCQC.values()).stream().filter(rank -> rank.getCode() == codeForSearch)
                .findAny().orElseThrow(() -> new IllegalArgumentException("no explorer rank"));
    }

}
