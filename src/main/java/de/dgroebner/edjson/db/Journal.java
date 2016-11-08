package de.dgroebner.edjson.db;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.dao.JournalDao;
import de.dgroebner.edjson.db.model.DBJournalModel;

/**
 * Methoden für die Datenbanktabelle 'journal' zur Speicherung der Journaleinträge
 * 
 * @author dgroebner
 */
public class Journal extends AbstractDBTable {

    /**
     * Constructor.
     *
     * @param dbi {@link DBI}
     */
    public Journal(final DBI dbi) {
        super(dbi);
    }

    /**
     * Schreibt einen neuen Journaleintrag in die Datenbank. Die ID des geschriebenen Datensatz kann anschließend über
     * die Methode {@link Journal#getDatabaseId()} abgefragt werden.
     * 
     * @param journal {@link DBJournalModel}
     */
    public void writeJournal(final DBJournalModel journal) {
        final JournalDao journalDao = getDbi().open(JournalDao.class);
        try {
            setId(journalDao.insert(journal.getJournalfileId(), journal.getTimestamp(), journal.getEvent(),
                    journal.getMessage()));
        } finally {
            journalDao.close();
        }
    }

}
