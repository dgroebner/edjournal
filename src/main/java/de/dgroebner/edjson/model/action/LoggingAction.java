package de.dgroebner.edjson.model.action;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.model.JournalModel;

/**
 * Standardaktion die nur Loggingausgaben tätigt
 * 
 * @author dgroebner
 */
public class LoggingAction extends AbstractAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAction.class);

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final JournalModel model) {
        LOGGER.info("Loggingaction Journaleintrag {} mit Model: {}", Integer.toString(journalId), model.toString());
    }

}
