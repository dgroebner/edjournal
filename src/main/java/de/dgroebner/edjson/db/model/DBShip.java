package de.dgroebner.edjson.db.model;


/**
 * Modellklasse für die Datenbanktabelle 'ship'
 * 
 * @author dgroebner
 */
public class DBShip extends AbstractModel {

    private int edId;

    private String type;

    private String callsign;

    private String inaraUrl;

    /**
     * Constructor.
     */
    public DBShip() {
        // Default Constructor
    }

    /**
     * Constructor.
     *
     * @param id int
     * @param edId int
     * @param type {@link String}
     * @param callsign {@link String}
     * @param inaraUrl {@link String}
     */
    public DBShip(final int id, final int edId, final String type, final String callsign, final String inaraUrl) {
        super(id);
        this.edId = edId;
        this.type = type;
        this.callsign = callsign;
        this.inaraUrl = inaraUrl;
    }

    public final int getEdId() {
        return edId;
    }

    public final void setEdId(int edId) {
        this.edId = edId;
    }

    public final String getType() {
        return type;
    }

    public final void setType(String type) {
        this.type = type;
    }

    public final String getCallsign() {
        return callsign;
    }

    public final void setCallsign(String callsign) {
        this.callsign = callsign;
    }

    public final String getInaraUrl() {
        return inaraUrl;
    }

    public final void setInaraUrl(String inaraUrl) {
        this.inaraUrl = inaraUrl;
    }

}
