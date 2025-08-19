package com.example.Bootcamp.kafkaAvro;

import com.example.avro.OrderCreatedEvent;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class AvroOrderProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        props.put("schema.registry.url", "http://localhost:8081");

        Producer<String, OrderCreatedEvent> producer = new KafkaProducer<>(props);

        OrderCreatedEvent event = OrderCreatedEvent.newBuilder()
                .setOrderId("O123")
                .setCustomerId("U456")
                .setOrderTotal(299.99)
                .setCreatedAt("2025-08-19")
                .build();

        ProducerRecord<String, OrderCreatedEvent> record =
                new ProducerRecord<>("order-events-avro", event.getOrderId().toString(), event);

        producer.send(record, (metadata, exception) -> {
            if (exception == null) {
                System.out.println("Sent Avro message to: " + metadata.topic());
            } else {
                exception.printStackTrace();
            }
        });

        producer.close();
    }
}

