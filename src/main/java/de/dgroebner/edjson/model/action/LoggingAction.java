package de.dgroebner.edjson.model.action;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.model.JournalModel;

/**
 * Standardaktion die nur Loggingausgaben tätigt
 * 
 * @author dgroebner
 * @param <M> {@link JournalModel}
 */
public class LoggingAction<M extends JournalModel> extends AbstractAction<M> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAction.class);

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final M model) {
        LOGGER.info("Loggingaction Journaleintrag {} mit Model: {}", Integer.toString(journalId), model.toString());
    }

}
