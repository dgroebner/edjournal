package de.dgroebner.edjson.processor;

import java.io.File;

import org.apache.camel.Exchange;

import de.dgroebner.edjson.db.JournalFile;

public class FileAlreadyReadCheck extends AbstractProcessor {

    @Override
    public void process(final Exchange exchange) throws Exception {
        final File file = exchange.getIn().getBody(File.class);
        if (file != null && file.getName().endsWith(".log")) {
            final JournalFile fileTable = new JournalFile(getDbi(), file);
            if (fileTable.isFileAlreadyParsed()) {
                exchange.setProperty(Exchange.ROUTE_STOP, Boolean.TRUE);
            } else {
                exchange.setProperty("edJournalFileId", Integer.valueOf(fileTable.save()));
                exchange.setProperty("edJournalFileName", file.getAbsolutePath());
            }
        } else {
            exchange.setProperty(Exchange.ROUTE_STOP, Boolean.TRUE);
        }
    }

}
