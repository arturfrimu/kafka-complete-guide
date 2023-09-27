package com.arturfrimu.kafka.conduktor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Slf4j
@Component
public class ProducerDemoWithCallback {
    public static void main(String[] args) {
        log.info("I'm a kafka producer with callback");

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        properties.setProperty("batch.size", "400");

        // properties.setProperty("partitioner.class", RoundRobinPartitioner.class.getName());

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(properties)) {

            for (int j = 1; j <= 10; j++) {
                for (int i = 1; i <= 30; i++) {
                    ProducerRecord<String, String> producerRecord = new ProducerRecord<>("demo_topic", "Nr: {%s : %s}"
                            .formatted(j, i));
                    producer.send(producerRecord, new Callback() {
                        @Override
                        public void onCompletion(RecordMetadata metadata, Exception exception) {
                            // executes every time a record successfully sent or an exception is thrown
                            if (exception == null) {
                                // the record was successfully sent
                                log.info("Received new metadata\n" +
                                        "Topic: " + metadata.topic() + "\n" +
                                        "Partition: " + metadata.partition() + "\n" +
                                        "Offset: " + metadata.offset() + "\n" +
                                        "Timestamp: " + metadata.timestamp());
                            } else {
                                log.error("Error while producing", exception);
                            }
                        }
                    });
                }

                sleep(500);
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
