package de.dgroebner.edjson.model.action;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Properties;
import de.dgroebner.edjson.db.Properties.ENTRIES;
import de.dgroebner.edjson.model.data.Undocked;

/**
 * Action für das JournalModell {@link Undocked}
 * 
 * @author dgroebner
 */
public class UndockedAction extends AbstractAction<Undocked> {

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final Undocked model) {
        new Properties(dbi).deleteEntry(ENTRIES.CURRENT_LOCAL_FACTION);
    }

}
