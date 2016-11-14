package de.dgroebner.edjson.db.model;

import java.time.LocalDateTime;


/**
 * Modellklasse für die Datenbanktabelle 'commlog'
 * 
 * @author dgroebner
 */
public class DBCommlog extends AbstractModel {

    private int journalId;

    private LocalDateTime timestamp;

    private String sender;

    private String reciever;

    private String channel;

    private String message;

    /**
     * Constructor.
     */
    public DBCommlog() {
        // Default Constructor
    }

    /**
     * Constructor.
     *
     * @param id int
     * @param journalId int
     * @param timestamp {@link LocalDateTime}
     * @param sender {@link String}
     * @param reciever {@link String}
     * @param channel {@link String}
     * @param message {@link String}
     */
    public DBCommlog(final int id, final int journalId, final LocalDateTime timestamp, final String sender,
            final String reciever, final String channel, final String message) {
        super(id);
        this.journalId = journalId;
        this.timestamp = timestamp;
        this.sender = sender;
        this.reciever = reciever;
        this.channel = channel;
        this.message = message;
    }

    public final int getJournalId() {
        return journalId;
    }

    public final void setJournalId(int journalId) {
        this.journalId = journalId;
    }

    public final LocalDateTime getTimestamp() {
        return timestamp;
    }

    public final void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public final String getSender() {
        return sender;
    }

    public final void setSender(String sender) {
        this.sender = sender;
    }

    public final String getReciever() {
        return reciever;
    }

    public final void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public final String getChannel() {
        return channel;
    }

    public final void setChannel(String channel) {
        this.channel = channel;
    }

    public final String getMessage() {
        return message;
    }

    public final void setMessage(String message) {
        this.message = message;
    }

}
