package de.dgroebner.edjson.model.data;

import java.util.Arrays;

/**
 * Enum der Kampfränge von Elite Dangerous
 * 
 * @author dgroebner
 */
public enum RankCombat {
    
    /* @formatter:off */
    HARMLESS("Harmless", 0),
    MOSTLY_HARMLESS("Mostly Harmless", 1),
    NOVICE("Novice", 2),
    COMPETENT("Competent", 3),
    EXPERT("Expert", 4),
    MASTER("Master", 5),
    DANGEROUS("Dangerous", 6),
    DEADLY("Deadly", 7),
    ELITE("Elite", 8);
    /* @formatter:on */
    
    private final int code;
    
    private final String name;
    
    RankCombat(final String name, final int code) {
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
     * Liefert den {@link RankCombat} für den übergebenen code
     * 
     * @param codeForSearch int
     * @return {@link RankCombat}
     * @throws IllegalArgumentException wenn der code nicht zugeordnet werden konnte
     */
    public static RankCombat forCode(final int codeForSearch) {
        return Arrays.asList(RankCombat.values()).stream().filter(rank -> rank.getCode() == codeForSearch).findAny()
                .orElseThrow(() -> new IllegalArgumentException("no combat rank"));
    }

}
