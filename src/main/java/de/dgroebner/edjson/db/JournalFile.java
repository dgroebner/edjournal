package de.dgroebner.edjson.db;

import java.io.File;
import java.time.LocalDateTime;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.dao.JournalFileDao;

/**
 * Methoden für die Datenbanktabelle JournalFile zur Speicherung der verarbeiteten Dateien
 * 
 * @author dgroebner
 */
public class JournalFile extends AbstractDBTable {

    private final File file;

    /**
     * Constructor.
     *
     * @param dbi {@link DBI} Datenbankverbindungsobjekt
     * @param file {@link File} aktuell bearbeitete Datei
     */
    public JournalFile(final DBI dbi, final File file) {
        super(dbi);
        this.file = file;
    }

    /**
     * Schreibt die Datei als neue Datei in die Datenbank. Die ID des geschriebenen Datensatz kann anschließend über die
     * Methode {@link JournalFile#getDatabaseId()} abgefragt werden.
     */
    public void writeNewFileToDb() {
        final JournalFileDao journalFileDao = getDbi().open(JournalFileDao.class);
        try {
            setId(journalFileDao.insert(file.getName(), LocalDateTime.now()));
        } finally {
            journalFileDao.close();
        }
    }

    /**
     * Prüft ob die Datei bereits vorhanden ist
     * 
     * @return boolean
     */
    public boolean isFileAlreadyPared() {
        final JournalFileDao journalFileDao = getDbi().open(JournalFileDao.class);
        try {
            return journalFileDao.countByName(file.getName()) > 0;
        } finally {
            journalFileDao.close();
        }
    }

}
