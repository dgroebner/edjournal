package de.dgroebner.edjson.db.model;

/**
 * Abstrakte Modellklasse für die Datenbanktabellen
 * 
 * @author dgroebner
 */
public class AbstractModel {

    private int id;

    /**
     * Constructor.
     */
    public AbstractModel() {
        // Default Constructor
    }

    /**
     * Constructor.
     *
     * @param id int
     */
    public AbstractModel(final int id) {
        this.id = id;
    }

    public final int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }

}
