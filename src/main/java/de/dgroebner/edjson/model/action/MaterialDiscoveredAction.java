package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.MaterialDiscovered.Fields.CATEGORY;
import static de.dgroebner.edjson.model.data.MaterialDiscovered.Fields.DISCOVERY_NUMBER;
import static de.dgroebner.edjson.model.data.MaterialDiscovered.Fields.NAME;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Material;
import de.dgroebner.edjson.db.model.DBMaterial;
import de.dgroebner.edjson.model.data.MaterialDiscovered;

/**
 * Action für das JournalModell {@link MaterialDiscovered}
 * 
 * @author dgroebner
 */
public class MaterialDiscoveredAction extends AbstractAction<MaterialDiscovered> {

    @Override
    protected String buildJournalMessage(DBI dbi, MaterialDiscovered model) {
        final Material dao = new Material(dbi);
        final DBMaterial material = dao.getMaterialByEdName(model.getValueAsString(NAME));
        final String message;
        if (material != null) {
            final int newStock = material.getStock() + model.getValueAsInt(DISCOVERY_NUMBER);
            final String name = StringUtils.isBlank(material.getName()) ? model.getValueAsString(NAME) : material
                    .getName();
            message = String.format("Material %s der Kategorie %s gefunden. Neuer Bestand: %d", name,
                    model.getValueAsString(CATEGORY), Integer.valueOf(newStock));
        } else {
            message = String.format("Neues Material %s der Kategorie %s gefunden. Anzahl: %d",
                    model.getValueAsString(NAME), model.getValueAsString(CATEGORY),
                    Integer.valueOf(model.getValueAsInt(DISCOVERY_NUMBER)));
        }
        new Material(dbi).save(model.getValueAsString(NAME), model.getValueAsString(CATEGORY),
                model.getValueAsInt(DISCOVERY_NUMBER));
        return message;
    }

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final MaterialDiscovered model) {
        // do nothing
    }

}
