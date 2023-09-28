package com.arturfrimu.kafka.conduktor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Slf4j
@Component
public class ProducerDemoKeys {
    public static void main(String[] args) {
        log.info("I'm a kafka producer with callback and with key");

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(properties)) {
            for (int j = 0; j < 3; j++) {
                for (int i = 1; i <= 5; i++) {

                    String topic = "demo_topic";
                    String key = "Key_%s".formatted(i);
                    String value = "Value: {%s}".formatted(i);

                    ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, value);

                    producer.send(producerRecord, (metadata, exception) -> {
                        if (exception == null) {
                            log.info("Key = " + key + " | Partition =  " + metadata.partition());
                        } else {
                            log.error("Error while producing", exception);
                        }
                    });
                    sleep(2000);
                }
            }

            producer.flush(); // tell the producer to send all data and block until done -- synchronous
        }
    }

    private static void sleep(final int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
