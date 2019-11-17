package com.learncamel.kafka;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * A Camel client that can route messages
 * from a kafka topic and log the output
 */
public class CamelKafkaConsumer {

    public static void main(String[] args) {
        CamelContext camelContext = new DefaultCamelContext();

        try {
            camelContext.addRoutes(new RouteBuilder() {
                @Override
                public void configure() {
                    from("kafka:camel-log-topic?brokers=localhost:9092&consumersCount=1&seekTo=beginning&groupId=group1")
                            .routeId("FromKafka")
                            .log("${body}");
                }
            });
            camelContext.start();
            Thread.sleep(5 * 60 * 1000);
            camelContext.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
