package com.sample;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.component.ComponentsBuilderFactory;
import org.apache.camel.model.dataformat.JsonLibrary;

public class TwitterSearchRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        ComponentsBuilderFactory.jms().connectionFactory(connectionFactory).register(getCamelContext(), "jms");

        from("twitter-search:{{twitter.word}}?accessToken={{twitter.accesstoken}}&accessTokenSecret={{twitter.accesstokensecret}}&consumerKey={{twitter.apikey}}&consumerSecret={{twitter.apisecretkey}}&delay={{twitter.interval}}")
            .bean("com.sample.bean.TweetInfoBean", "MakeTweetInfo")
            .marshal().json(JsonLibrary.Jackson, true)
            .to("jms:queue:orders")
            .log("${body}");
        
    }
    
}
