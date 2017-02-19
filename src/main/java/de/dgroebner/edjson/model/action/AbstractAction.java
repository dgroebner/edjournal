package de.dgroebner.edjson.model.action;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Journal;
import de.dgroebner.edjson.db.model.DBJournal;
import de.dgroebner.edjson.model.JournalModel;

/**
 * Abstrakte Action-Implementierung für die Methode
 * {@link JournalEventAction#writeJournalToDatabase(DBI, int, JournalModel)}
 * 
 * @author dgroebner
 * @param <M> {@link JournalModel}
 */
public abstract class AbstractAction<M extends JournalModel> implements JournalEventAction<M> {

    @Override
    public int writeJournalToDatabase(final DBI dbi, final int journalFileId, final M model) {
        /* @formatter:off */
        final DBJournal dbJournal = DBJournal.builder()
                .event(model.getEvent())
                .timestamp(model.getTimestamp())
                .journalfileId(journalFileId)
                .message(buildJournalMessage(dbi, model))
                .build();
        /* @formatter:on */
        return new Journal(dbi).writeJournal(dbJournal);
    }

    /**
     * Gibt die Nachricht für den Journaleintrag zurück
     * 
     * @param dbi {@link DBI} Datenbankzugriffsobjekt
     * @param model {@link JournalModel}
     * @return String
     */
    protected String buildJournalMessage(final DBI dbi, final M model) {
        return model.getMessage();
    }

}
