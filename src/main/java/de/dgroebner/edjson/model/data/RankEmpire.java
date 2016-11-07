package de.dgroebner.edjson.model.data;

import java.util.Arrays;

/**
 * Enum der Imperiumsränge von Elite Dangerous
 * 
 * @author dgroebner
 */
public enum RankEmpire {
    
    /* @formatter:off */
    NONE("None", 0),
    OUTSIDER("Outsider", 1),
    SERF("Serf", 2),
    MASTER("Master", 3),
    SQUIRE("Squire", 4),
    KNIGHT("Knight", 5),
    LORD("Lord", 6),
    BARON("Baron", 7),
    VISCOUNT("Viscount", 8),
    COUNT("Count", 9),
    EARL("Earl", 10),
    MARQUIS("Marquis", 11),
    DUKE("Duke", 12),
    PRINCE("Prince", 13),
    KING("King", 14);
    /* @formatter:on */
    
    private final int code;
    
    private final String name;
    
    RankEmpire(final String name, final int code) {
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
     * Liefert den {@link RankEmpire} für den übergebenen code
     * 
     * @param codeForSearch int
     * @return {@link RankEmpire}
     * @throws IllegalArgumentException wenn der code nicht zugeordnet werden konnte
     */
    public static RankEmpire forCode(final int codeForSearch) {
        return Arrays.asList(RankEmpire.values()).stream().filter(rank -> rank.getCode() == codeForSearch)
                .findAny().orElseThrow(() -> new IllegalArgumentException("no explorer rank"));
    }

}
