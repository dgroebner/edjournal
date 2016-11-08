package de.dgroebner.edjson.db.model;

import java.time.LocalDateTime;

/**
 * Modellklasse für die Datenbanktabelle 'journal'
 * 
 * @author dgroebner
 */
public class DBJournalModel extends AbstractModel {

    private int journalfileId;

    private LocalDateTime timestamp;

    private String event;

    private String message;

    /**
     * Constructor.
     */
    public DBJournalModel() {
        // Default Constructor
    }

    /**
     * Constructor.
     *
     * @param id int
     * @param journalfileId int
     * @param timestamp {@link LocalDateTime}
     * @param event {@link String}
     * @param message {@link String}
     */
    public DBJournalModel(final int id, final int journalfileId, final LocalDateTime timestamp, final String event,
            final String message) {
        super(id);
        this.journalfileId = journalfileId;
        this.timestamp = timestamp;
        this.event = event;
        this.message = message;
    }

    public final int getJournalfileId() {
        return journalfileId;
    }

    public final void setJournalfileId(int journalfileId) {
        this.journalfileId = journalfileId;
    }

    public final LocalDateTime getTimestamp() {
        return timestamp;
    }

    public final void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public final String getEvent() {
        return event;
    }

    public final void setEvent(String event) {
        this.event = event;
    }

    public final String getMessage() {
        return message;
    }

    public final void setMessage(String message) {
        this.message = message;
    }

}
