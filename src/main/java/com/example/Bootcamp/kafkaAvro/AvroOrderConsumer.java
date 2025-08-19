package com.example.Bootcamp.kafkaAvro;

import com.example.avro.OrderCreatedEvent;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class AvroOrderConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        props.put("schema.registry.url", "http://localhost:8081");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "avro-order-consumer-group");
        props.put("specific.avro.reader", "true"); // Ensures you get OrderCreatedEvent, not GenericRecord

        KafkaConsumer<String, OrderCreatedEvent> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("order-events-avro"));

        while (true) {
            ConsumerRecords<String, OrderCreatedEvent> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, OrderCreatedEvent> record : records) {
                System.out.println("Consumed Avro Event: " + record.value());
            }
        }
    }
}

