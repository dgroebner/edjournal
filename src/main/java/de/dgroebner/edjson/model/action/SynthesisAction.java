package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.Synthesis.Fields.MATERIALS;

import org.json.JSONObject;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Material;
import de.dgroebner.edjson.model.data.Synthesis;

/**
 * Action für das JournalModell {@link Synthesis}
 * 
 * @author dgroebner
 */
public class SynthesisAction extends AbstractAction<Synthesis> {

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final Synthesis model) {
        final Material materialDao = new Material(dbi);
        final JSONObject materials = model.getValueAsJsonObject(MATERIALS);
        materials.keys().forEachRemaining(
                key -> materialDao.save(key, null, ((Integer) materials.get(key)).intValue() * -1));
    }
}
