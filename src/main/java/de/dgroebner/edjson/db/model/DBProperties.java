package de.dgroebner.edjson.db.model;


/**
 * Modellklasse für die Datenbanktabelle 'properties'
 * 
 * @author dgroebner
 */
public class DBProperties extends AbstractModel {

    private String entry;

    private String value;

    /**
     * Constructor.
     */
    public DBProperties() {
        // Default Constructor
    }

    /**
     * Constructor.
     *
     * @param id int
     * @param entry String
     * @param value String
     */
    public DBProperties(final int id, final String entry, final String value) {
        super(id);
        this.entry = entry;
        this.value = value;
    }

    public final String getEntry() {
        return entry;
    }

    public final void setEntry(String entry) {
        this.entry = entry;
    }

    public final String getValue() {
        return value;
    }

    public final void setValue(String value) {
        this.value = value;
    }

}
