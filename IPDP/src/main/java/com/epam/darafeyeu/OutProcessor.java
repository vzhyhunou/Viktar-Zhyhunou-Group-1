package com.epam.darafeyeu;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by xdar on 23.2.15.
 */
public class OutProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        exchange.getOut().setBody("Bye!!!");

    }
}
