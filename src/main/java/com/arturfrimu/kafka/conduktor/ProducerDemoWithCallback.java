package com.arturfrimu.kafka.conduktor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
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

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(properties)) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("demo_topic", "I'm a kafka producer with callback");

            producer.send(producerRecord);

            producer.flush(); // tell the producer to send all data and block until done -- synchronous
        }
    }
}
