package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.MaterialCollected.Fields.CATEGORY;
import static de.dgroebner.edjson.model.data.MaterialCollected.Fields.COUNT;
import static de.dgroebner.edjson.model.data.MaterialCollected.Fields.NAME;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Material;
import de.dgroebner.edjson.db.model.DBMaterial;
import de.dgroebner.edjson.model.data.MaterialCollected;

/**
 * Action für das JournalModell {@link MaterialCollected}
 * 
 * @author dgroebner
 */
public class MaterialCollectedAction extends AbstractAction<MaterialCollected> {

    @Override
    protected String buildJournalMessage(DBI dbi, MaterialCollected model) {
        final Material dao = new Material(dbi);
        final DBMaterial material = dao.getMaterialByEdName(model.getValueAsString(NAME));
        final String message;
        if (material != null) {
            final int newStock = material.getStock() + model.getValueAsInt(COUNT);
            final String name = StringUtils.isBlank(material.getName()) ? model.getValueAsString(NAME) : material
                    .getName();
            message = String.format("Material %s der Kategorie %s gefunden. Neuer Bestand: %d", name,
                    model.getValueAsString(CATEGORY), Integer.valueOf(newStock));
        } else {
            message = String.format("Neues Material %s der Kategorie %s gefunden. Anzahl: %d",
                    model.getValueAsString(NAME), model.getValueAsString(CATEGORY),
                    Integer.valueOf(model.getValueAsInt(COUNT)));
        }
        new Material(dbi).save(model.getValueAsString(NAME), model.getValueAsString(CATEGORY),
                model.getValueAsInt(COUNT));
        return message;
    }

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final MaterialCollected model) {
        // do nothing
    }

}
