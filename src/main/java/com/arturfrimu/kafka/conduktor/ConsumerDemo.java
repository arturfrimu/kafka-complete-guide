package com.arturfrimu.kafka.conduktor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Component;

import java.time.Duration;
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

        // pool for data
        while (true) {
            log.info("Pooling");

            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(5000));

            records.forEach(record -> log.info("Key: %s, Value: %s, Partition: %s, Offset: %s".formatted(
                    record.key(),
                    record.value(),
                    record.partition(),
                    record.offset()))
            );
        }
    }
}
