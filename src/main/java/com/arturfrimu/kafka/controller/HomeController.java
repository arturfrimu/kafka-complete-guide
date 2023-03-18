package com.arturfrimu.kafka.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
public final class HomeController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public HomeController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public void sendMessage(@RequestBody MessageRequest request) {
        kafkaTemplate.send("test", request.message);
    }

    private record MessageRequest(String message) {
    }
}
