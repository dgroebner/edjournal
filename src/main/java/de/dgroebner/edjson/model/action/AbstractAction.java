package de.dgroebner.edjson.model.action;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Journal;
import de.dgroebner.edjson.db.model.DBJournalModel;
import de.dgroebner.edjson.model.JournalModel;

/**
 * Abstrakte Action-Implementierung für die Methode
 * {@link JournalEventAction#writeJournalToDatabase(DBI, int, JournalModel)}
 * 
 * @author dgroebner
 */
public abstract class AbstractAction implements JournalEventAction {

    @Override
    public int writeJournalToDatabase(final DBI dbi, final int journalFileId, final JournalModel model) {
        final DBJournalModel dbJournal = new DBJournalModel();
        dbJournal.setJournalfileId(journalFileId);
        dbJournal.setTimestamp(model.getTimestamp());
        dbJournal.setEvent(model.getEvent());
        dbJournal.setMessage(model.getMessage());

        final Journal journal = new Journal(dbi);
        journal.writeJournal(dbJournal);
        return journal.getDatabaseId();
    }

}
