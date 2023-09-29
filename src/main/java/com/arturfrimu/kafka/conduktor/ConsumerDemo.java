package com.arturfrimu.kafka.conduktor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;

@Slf4j
@Component
public class ConsumerDemo {
    public static void main(String[] args) {
        log.info("I'm a simple kafka consumer");

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");

        // create consumer config
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());

        properties.setProperty("group.id", "my-java-application");

        properties.setProperty("auto.offset.reset", "earliest"); //   none | earliest | latest

        // create a consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        // subscribe to a topic
        String topic = "demo_topic";
        consumer.subscribe(List.of(topic));
    }
}
