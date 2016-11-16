package de.dgroebner.edjson.db.model;

import java.time.LocalDateTime;

/**
 * Modellklasse für die Datenbanktabelle 'journalfile'
 * 
 * @author dgroebner
 */
public class DBJournalFile extends AbstractModel {

    private String filename;

    private LocalDateTime readDate;

    /**
     * Constructor.
     *
     * @param id int
     * @param filename {@link String}
     * @param readDate {@link LocalDateTime}
     */
    public DBJournalFile(final int id, final String filename, final LocalDateTime readDate) {
        super(id);
        this.filename = filename;
        this.readDate = readDate;
    }

    public final String getFilename() {
        return filename;
    }

    public final void setFilename(final String filename) {
        this.filename = filename;
    }

    public final LocalDateTime getReadDate() {
        return readDate;
    }

    public final void setReadDate(final LocalDateTime readDate) {
        this.readDate = readDate;
    }

}
