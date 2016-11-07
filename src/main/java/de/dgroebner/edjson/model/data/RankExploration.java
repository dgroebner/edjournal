package de.dgroebner.edjson.model.data;

import java.util.Arrays;

/**
 * Enum der Erkunderränge von Elite Dangerous
 * 
 * @author dgroebner
 */
public enum RankExploration {
    
    /* @formatter:off */
    AIMLESS("Aimless", 0),
    MOSTLY_AIMLESS("Mostly Aimless", 1),
    SCOUT("Scout", 2),
    SURVEYOR("Surveyor", 3),
    EXPLORER("Explorer", 4),
    PATHFINDER("Pathfinder", 5),
    RANGER("Ranger", 6),
    PIONEER("Pioneer", 7),
    ELITE("Elite", 8);
    /* @formatter:on */
    
    private final int code;
    
    private final String name;
    
    RankExploration(final String name, final int code) {
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
     * Liefert den {@link RankExploration} für den übergebenen code
     * 
     * @param codeForSearch int
     * @return {@link RankExploration}
     * @throws IllegalArgumentException wenn der code nicht zugeordnet werden konnte
     */
    public static RankExploration forCode(final int codeForSearch) {
        return Arrays.asList(RankExploration.values()).stream().filter(rank -> rank.getCode() == codeForSearch)
                .findAny().orElseThrow(() -> new IllegalArgumentException("no explorer rank"));
    }

}
