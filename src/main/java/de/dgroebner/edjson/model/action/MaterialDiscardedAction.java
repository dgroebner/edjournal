package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.MaterialDiscarded.Fields.CATEGORY;
import static de.dgroebner.edjson.model.data.MaterialDiscarded.Fields.COUNT;
import static de.dgroebner.edjson.model.data.MaterialDiscarded.Fields.NAME;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Material;
import de.dgroebner.edjson.model.data.MaterialDiscarded;

/**
 * Action für das JournalModell {@link MaterialDiscarded}
 * 
 * @author dgroebner
 */
public class MaterialDiscardedAction extends AbstractAction<MaterialDiscarded> {

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final MaterialDiscarded model) {
        new Material(dbi).save(model.getValueAsString(NAME), model.getValueAsString(CATEGORY),
                model.getValueAsInt(COUNT) * -1);
    }
}
