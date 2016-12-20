package de.dgroebner.edjson;

import org.apache.camel.builder.RouteBuilder;

import de.dgroebner.edjson.processor.FileAlreadyReadCheck;

public class EDJournalRouteBuilder extends RouteBuilder {

    private static final String DIRECTORY = "c:\\Users\\dgroebner\\Saved Games\\Frontier Developments\\Elite Dangerous\\";

    @Override
    public void configure() throws Exception {
        /* @formatter:off */
        from("file://" + DIRECTORY + "/")
          .bean(FileAlreadyReadCheck.class)
          .setHeader("edJournalFile", body())
          .to("stream:file?fileName=${exchangeProperty.edJournalFileName}&encoding=UTF-8&scanStream=true&scanStreamDelay=1000")
          .bean(EDJournalParser.class);
        /* @formatter:on */
    }

}
