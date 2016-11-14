package de.dgroebner.edjson.db.model;


/**
 * Modellklasse für die Datenbanktabelle 'material'
 * 
 * @author dgroebner
 */
public class DBMaterial extends AbstractModel {

    private String name;

    private String inaraUrl;

    private String kuerzel;

    private String edName;

    private String category;

    private int stock;

    /**
     * Constructor.
     */
    public DBMaterial() {
        // Default Constructor
    }

    /**
     * Constructor.
     *
     * @param id int
     * @param name {@link String}
     * @param inaraUrl {@link String}
     * @param kuerzel {@link String}
     * @param edName {@link String}
     * @param category {@link String}
     * @param stock {@link String}
     */
    @SuppressWarnings("squid:S00107")
    public DBMaterial(final int id, final String name, final String inaraUrl, final String kuerzel,
            final String edName, final String category, final int stock) {
        super(id);
        this.name = name;
        this.inaraUrl = inaraUrl;
        this.kuerzel = kuerzel;
        this.edName = edName;
        this.category = category;
        this.stock = stock;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final String getInaraUrl() {
        return inaraUrl;
    }

    public final void setInaraUrl(String inaraUrl) {
        this.inaraUrl = inaraUrl;
    }

    public final String getKuerzel() {
        return kuerzel;
    }

    public final void setKuerzel(String kuerzel) {
        this.kuerzel = kuerzel;
    }

    public final String getEdName() {
        return edName;
    }

    public final void setEdName(String edName) {
        this.edName = edName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public final int getStock() {
        return stock;
    }

    public final void setStock(int stock) {
        this.stock = stock;
    }

}
