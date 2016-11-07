package de.dgroebner.edjson.model.data;

import java.util.Arrays;

/**
 * Enum der Handelsränge von Elite Dangerous
 * 
 * @author dgroebner
 */
public enum RankTrade {
    
    /* @formatter:off */
    PENNILESS("Penniless", 0),
    MOSTLY_PENNILESS("Mostly Pennliess", 1),
    PEDDLER("Peddler", 2),
    DEALER("Dealer", 3),
    MERCHANT("Merchant", 4),
    BROKER("Broker", 5),
    ENTREPRENEUR("Entrepreneur", 6),
    TYCOON("Tycoon", 7),
    ELITE("Elite", 8);
    /* @formatter:on */
    
    private final int code;
    
    private final String name;
    
    RankTrade(final String name, final int code) {
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
     * Liefert den {@link RankTrade} für den übergebenen code
     * 
     * @param codeForSearch int
     * @return {@link RankTrade}
     * @throws IllegalArgumentException wenn der code nicht zugeordnet werden konnte
     */
    public static RankTrade forCode(final int codeForSearch) {
        return Arrays.asList(RankTrade.values()).stream().filter(rank -> rank.getCode() == codeForSearch).findAny()
                .orElseThrow(() -> new IllegalArgumentException("no trade rank"));
    }

}
