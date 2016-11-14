package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.ReceiveText.Fields.CHANNEL;
import static de.dgroebner.edjson.model.data.ReceiveText.Fields.FROM;
import static de.dgroebner.edjson.model.data.ReceiveText.Fields.FROM_LOCALISED;
import static de.dgroebner.edjson.model.data.ReceiveText.Fields.MESSAGE;
import static de.dgroebner.edjson.model.data.ReceiveText.Fields.MESSAGE_LOCALISED;
import static de.dgroebner.edjson.model.data.ReceiveText.Fields.TIMESTAMP;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Commlog;
import de.dgroebner.edjson.model.data.ReceiveText;
import de.dgroebner.edjson.model.data.SendText;

/**
 * Action für das JournalModell {@link SendText}
 * 
 * @author dgroebner
 */
public class ReceiveTextAction extends AbstractAction<ReceiveText> {

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final ReceiveText model) {
        final String message = StringUtils.defaultIfBlank(model.getValueAsString(MESSAGE_LOCALISED),
                model.getValueAsString(MESSAGE));
        
        String sender = StringUtils
                .defaultIfBlank(model.getValueAsString(FROM_LOCALISED),
                model.getValueAsString(FROM));
        sender = StringUtils.removeStart(sender, "&");

        new Commlog(dbi).save(journalId, model.getValueAsLocalDateTime(TIMESTAMP), sender, null,
                model.getValueAsString(CHANNEL), message);
    }

}
