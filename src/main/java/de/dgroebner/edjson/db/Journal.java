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
     * Schreibt einen neuen Journaleintrag in die Datenbank und gibt die id zurück
     * 
     * @param journal {@link DBJournalModel}
     * @return int
     */
    public int writeJournal(final DBJournalModel journal) {
        final JournalDao journalDao = getDbi().open(JournalDao.class);
        try {
            return journalDao.insert(journal.getJournalfileId(), journal.getTimestamp(), journal.getEvent(),
                    journal.getMessage());
        } finally {
            journalDao.close();
        }
    }

}
