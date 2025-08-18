package com.example.Bootcamp.controller;
import com.example.Bootcamp.Kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaMessageController {

    @Autowired
    private KafkaProducer producer;

    @PostMapping("/publish")
    public String sendMessage(@RequestParam("message") String message) {
        producer.sendMessage(message);
        return "Message sent to Kafka topic";
    }
}

