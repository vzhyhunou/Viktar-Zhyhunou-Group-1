package com.epam.darafeyeu;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by xdar on 23.2.15.
 */
public class ChannelAdapter implements Processor {

    @Override
    public void process ( Exchange exchange ) throws Exception {

        String adaptedBody = exchange.getIn().getBody( String.class ) + " (adapted to send to socket)";
        exchange.getOut().setBody( adaptedBody );

    }
}
