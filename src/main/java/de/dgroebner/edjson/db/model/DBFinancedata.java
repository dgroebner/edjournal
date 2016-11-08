package de.dgroebner.edjson.db.model;

import java.time.LocalDateTime;

/**
 * Modellklasse für die Datenbanktabelle 'fincancedata'
 * 
 * @author dgroebner
 */
public class DBFinancedata extends AbstractModel {

    private int journalId;

    private LocalDateTime valutadatum;

    private int amount;

    private String category;

    private String remark;

    /**
     * Constructor.
     */
    public DBFinancedata() {
        // Default Constructor
    }

    /**
     * Constructor.
     *
     * @param id int
     * @param journalId int
     * @param valutadatum LocalDateTime
     * @param amount int
     * @param category String
     * @param remark String
     */
    public DBFinancedata(final int id, final int journalId, final LocalDateTime valutadatum, final int amount,
            final String category, final String remark) {
        super(id);
        this.journalId = journalId;
        this.valutadatum = valutadatum;
        this.amount = amount;
        this.category = category;
        this.remark = remark;
    }

    public final int getJournalId() {
        return journalId;
    }

    public final void setJournalId(int journalId) {
        this.journalId = journalId;
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

}
