package de.dgroebner.edjson.db.model;

import java.time.LocalDateTime;

/**
 * Modellklasse für den View 'vfinancelog'
 * 
 * @author dgroebner
 */
public class VFinanceLog extends AbstractModel {

    private LocalDateTime valutadatum;

    private int amount;

    private String category;

    private String remark;

    private String factionName;

    private String factionUrl;

    /**
     * Constructor.
     */
    public VFinanceLog() {
        // Default Constructor
    }

    /**
     * Constructor.
     *
     * @param valutadatum {@link LocalDateTime}
     * @param amount int
     * @param category {@link String}
     * @param remark {@link String}
     * @param factionName {@link String}
     * @param factionUrl {@link String}
     */
    public VFinanceLog(final LocalDateTime valutadatum, final int amount, final String category, final String remark,
            final String factionName, final String factionUrl) {
        super(0);
        this.factionName = factionName;
        this.valutadatum = valutadatum;
        this.amount = amount;
        this.category = category;
        this.remark = remark;
        this.factionUrl = factionUrl;
    }

    public final LocalDateTime getValutadatum() {
        return valutadatum;
    }

    public final void setValutadatum(LocalDateTime valutadatum) {
        this.valutadatum = valutadatum;
    }

    public final int getAmount() {
        return amount;
    }

    public final void setAmount(int amount) {
        this.amount = amount;
    }

    public final String getCategory() {
        return category;
    }

    public final void setCategory(String category) {
        this.category = category;
    }

    public final String getRemark() {
        return remark;
    }

    public final void setRemark(String remark) {
        this.remark = remark;
    }

    public final String getFactionName() {
        return factionName;
    }

    public final void setFactionName(String factionName) {
        this.factionName = factionName;
    }

    public final String getFactionUrl() {
        return factionUrl;
    }

    public final void setFactionUrl(String factionUrl) {
        this.factionUrl = factionUrl;
    }

}
