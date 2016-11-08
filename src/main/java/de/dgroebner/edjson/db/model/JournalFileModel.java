package de.dgroebner.edjson.db.model;

import java.util.Date;

/**
 * Modellklasse für die Datenbanktabelle 'journalfile'
 * 
 * @author dgroebner
 */
public class JournalFileModel extends AbstractModel {

    private String filename;

    private Date readDate;

    /**
     * Constructor.
     *
     * @param id int
     * @param filename {@link String}
     * @param readDate {@link Date}
     */
    public JournalFileModel(final int id, final String filename, final Date readDate) {
        super(id);
        this.filename = filename;
        this.readDate = readDate;
    }

    public final String getFilename() {
        return filename;
    }

    public final void setFilename(String filename) {
        this.filename = filename;
    }

    public final Date getReadDate() {
        return readDate;
    }

    public final void setReadDate(Date readDate) {
        this.readDate = readDate;
    }

}
