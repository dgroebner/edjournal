package de.dgroebner.edjson.db;

import java.util.List;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.dao.JournalDao;
import de.dgroebner.edjson.db.model.DBJournal;

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
     * @param journal {@link DBJournal}
     * @return int
     */
    public int writeJournal(final DBJournal journal) {
        final JournalDao journalDao = getDbi().open(JournalDao.class);
        try {
            return journalDao.insert(journal.getJournalfileId(), journal.getTimestamp(), journal.getEvent(),
                    journal.getMessage());
        } finally {
            journalDao.close();
        }
    }

    /**
     * Liefert eine Liste der Journaleinträge zurück
     * 
     * @return {@link List} von {@link DBJournal}
     */
    public List<DBJournal> listJournals() {
        final JournalDao journalDao = getDbi().open(JournalDao.class);
        try {
            return journalDao.list();
        } finally {
            journalDao.close();
        }
    }

}
