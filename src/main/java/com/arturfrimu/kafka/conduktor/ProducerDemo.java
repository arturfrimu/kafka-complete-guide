package com.arturfrimu.kafka.conduktor;

import com.fasterxml.jackson.datatype.jdk8.StreamSerializer;
import lombok.extern.slf4j.Slf4j;
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
    }
}
