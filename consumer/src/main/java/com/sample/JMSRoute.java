package com.sample;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.component.ComponentsBuilderFactory;
import org.apache.camel.component.jms.JmsComponent;

public class JMSRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        ComponentsBuilderFactory.jms().connectionFactory(connectionFactory).register(getCamelContext(), "jms");

        from("jms:queue:orders")
            .log("Get Message is: ${body}");
        
    }
    
}
