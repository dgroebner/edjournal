package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.EngineerCraft.Fields.BLUEPRINT;
import static de.dgroebner.edjson.model.data.EngineerCraft.Fields.ENGINEER;
import static de.dgroebner.edjson.model.data.EngineerCraft.Fields.INGREDIENTS;
import static de.dgroebner.edjson.model.data.EngineerCraft.Fields.LEVEL;

import org.json.JSONObject;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Material;
import de.dgroebner.edjson.model.data.EngineerCraft;

/**
 * Action für das JournalModell {@link EngineerCraft}
 * 
 * @author dgroebner
 */
public class EngineerCraftAction extends AbstractAction<EngineerCraft> {

    @Override
    protected String buildJournalMessage(final DBI dbi, final EngineerCraft model) {
        return String.format("Ingenieurmodifikation %s (Level %s) durch %s erstellt",
                model.getValueAsString(BLUEPRINT), Integer.toString(model.getValueAsInt(LEVEL)),
                model.getValueAsString(ENGINEER));
    }

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final EngineerCraft model) {
        final Material materialDao = new Material(dbi);
        final JSONObject materials = model.getValueAsJsonObject(INGREDIENTS);
        materials.keys().forEachRemaining(
                key -> materialDao.save(key, null, ((Integer) materials.get(key)).intValue() * -1));
    }

}
