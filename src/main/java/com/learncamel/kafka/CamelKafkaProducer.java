package com.learncamel.kafka;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaComponent;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.impl.DefaultCamelContext;

import java.util.HashMap;
import java.util.Map;

public class CamelKafkaProducer {

    public static void main(String[] args) {
        final CamelContext camelContext = new DefaultCamelContext();

        try {
            camelContext.addRoutes(new RouteBuilder() {
                @Override
                public void configure() {
                    KafkaComponent kafkaComponent = new KafkaComponent();
                    kafkaComponent.setBrokers("localhost:9092");
                    camelContext.addComponent("kafka", kafkaComponent);

                    from("direct:pushToTopic")
                            .routeId("ProducerKafka") // set a name for this route
                            .to("kafka:camel-log-topic")
                            .log("${headers}");
                }
            });

            ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
            camelContext.start();

            Map<String, Object> headers = new HashMap<>();
            headers.put(KafkaConstants.PARTITION_KEY, 0);
            headers.put(KafkaConstants.KEY, "1");


            for (int i = 0; i < 50; i++) {
                producerTemplate.sendBodyAndHeaders("direct:pushToTopic", "Ready Player: " + i, headers);
            }

            Thread.sleep(2000); // min * sec * millis
            camelContext.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
