package com.epam.darafeyeu;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xdar on 23.2.15.
 */

public final class CamelServiceSpringDSL implements CamelService {
    private static final String IN_ENDPOINT = "direct:in";
    private static final String OUT_ENDPOINT = "outProcessor";

    private CamelContext camelContext;
    private ProducerTemplate producer;
    private ConsumerTemplate consumer;

    public CamelServiceSpringDSL() {
        camelContext = (CamelContext) new ClassPathXmlApplicationContext("spring-camel-context.xml")
                .getBean("camelContext");
        producer = camelContext.createProducerTemplate();
        consumer = camelContext.createConsumerTemplate();
    }

    @Override
    public void send(final String message){
        producer.sendBody( IN_ENDPOINT, message );
    }

    @Override
    public String receive(){
        return (String) consumer.receiveBody( OUT_ENDPOINT );
    }
}
