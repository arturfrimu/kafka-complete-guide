package com.arturfrimu.kafka.conduktor;

import com.fasterxml.jackson.datatype.jdk8.StreamSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Slf4j
@Component
public class ProducerDemo {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.setProperty("key.serializer", StreamSerializer.class.getName());
        properties.setProperty("value.serializer", StreamSerializer.class.getName());

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(properties)) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("demo.topic", "hello world");

            producer.send(producerRecord);

            producer.flush(); // tell the producer to send all data and block until done -- synchronous
        }
    }
}
