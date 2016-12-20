package de.dgroebner.edjson.processor;

import java.io.File;

import org.apache.camel.Exchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.model.EDJournalEvents;
import de.dgroebner.edjson.model.JournalModel;

public class EDJSONParser extends AbstractProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(EDJSONParser.class);

    @Override
    public void process(final Exchange exchange) throws Exception {
        final String fileline = exchange.getIn().getBody(String.class);
        final int fileId = exchange.getProperty("edJournalFileId", Integer.class).intValue();
        final JSONObject obj = new JSONObject(fileline);

        final EDJournalEvents event = EDJournalEvents.forCode(obj.getString("event"));
        final JournalModel model = event.getModel(obj);
        final int journalId = event.getAction().writeJournalToDatabase(getDbi(), fileId, model);
        event.getAction().doActionOn(getDbi(), journalId, model);

        LOGGER.info("{} : {} : {}", exchange.getProperty("edJournalFile", File.class).getAbsolutePath(),
                obj.getString("timestamp"), obj.get("event"));
    }

}
