package de.dgroebner.edjson.db;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Properties.ENTRIES;
import de.dgroebner.edjson.db.dao.CommlogDao;

/**
 * Methoden für die Datenbanktabelle 'commlog' zur Speicherung des Kommunikationsprotokolls
 * 
 * @author dgroebner
 */
public class Commlog extends AbstractDBTable {

    /**
     * Constructor.
     *
     * @param dbi {@link DBI}
     */
    public Commlog(final DBI dbi) {
        super(dbi);
    }

    /**
     * Schreibt ein neues Kommunikationsprotokoll in die Datenbank.
     * 
     * @param journalId int
     * @param timestamp {@link LocalDateTime}
     * @param sender {@link String}
     * @param receiver {@link String}
     * @param channel {@link String}
     * @param message {@link String}
     */
    public void save(final int journalId, final LocalDateTime timestamp, final String sender,
            final String receiver, final String channel, final String message) {
        final String commander = new Properties(getDbi()).getValueForEntry(ENTRIES.CURRENT_COMMANDER);
        final String defaultSender = StringUtils.defaultIfBlank(sender, commander);
        final String defaultReceiver = StringUtils.defaultIfBlank(receiver, commander);
        final CommlogDao dao = getDbi().open(CommlogDao.class);
        try {
            dao.insert(journalId, timestamp, defaultSender, defaultReceiver, channel, message);
        } finally {
            dao.close();
        }
    }

}
