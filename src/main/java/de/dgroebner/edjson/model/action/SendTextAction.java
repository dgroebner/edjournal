package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.SendText.Fields.MESSAGE;
import static de.dgroebner.edjson.model.data.SendText.Fields.TIMESTAMP;
import static de.dgroebner.edjson.model.data.SendText.Fields.TO;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Commlog;
import de.dgroebner.edjson.model.data.SendText;

/**
 * Action für das JournalModell {@link SendText}
 * 
 * @author dgroebner
 */
public class SendTextAction extends AbstractAction<SendText> {

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final SendText model) {
        new Commlog(dbi).save(journalId, model.getValueAsLocalDateTime(TIMESTAMP), null, model.getValueAsString(TO),
                null, model.getValueAsString(MESSAGE));
    }

}
