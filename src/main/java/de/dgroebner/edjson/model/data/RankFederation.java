package de.dgroebner.edjson.model.data;

import java.util.Arrays;

/**
 * Enum der Förderungsränge von Elite Dangerous
 * 
 * @author dgroebner
 */
public enum RankFederation {
    
    /* @formatter:off */
    NONE("None", 0),
    RECRUIT("Recruit", 1),
    CADET("Cadet", 2),
    MIDSHIPMAN("Midshipman", 3),
    PETTY_OFFICER("Petty Officer", 4),
    CHIEF_PETTY_OFFICER("Chief Petty Officer", 5),
    WARRANT_OFFICER("Warrant Officer", 6),
    ENSIGN("Ensign", 7),
    LIEUTENANT("Lieutenant", 8),
    LT_COMMANDER("Lt. Commander", 9),
    POST_COMMANDER("Post Commander", 10),
    POST_CAPTAIN("Post Captain", 11),
    REAR_ADMIRAL("Rear Admiral", 12),
    VICE_ADMIRAL("Vice Admiral", 13),
    ADMIRAL("Admiral", 14);
    /* @formatter:on */
    
    private final int code;
    
    private final String name;
    
    RankFederation(final String name, final int code) {
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
     * Liefert den {@link RankFederation} für den übergebenen code
     * 
     * @param codeForSearch int
     * @return {@link RankFederation}
     * @throws IllegalArgumentException wenn der code nicht zugeordnet werden konnte
     */
    public static RankFederation forCode(final int codeForSearch) {
        return Arrays.asList(RankFederation.values()).stream().filter(rank -> rank.getCode() == codeForSearch)
                .findAny().orElseThrow(() -> new IllegalArgumentException("no explorer rank"));
    }

}
