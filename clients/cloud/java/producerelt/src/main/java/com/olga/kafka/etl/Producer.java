package com.olga.kafka.etl;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class Producer {
    // The topic we are going to write records to
    private static final String KAFKA_TOPIC_NAME = "very_simple_topic";

    public static void main(String[] args) {
        // Set producer configuration properties
        final Properties producerProps = new Properties();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);

        // Create a new producer
        try (final KafkaProducer<String, String> producer = new KafkaProducer<>(producerProps)) {
            // Write 10 records into the topic
            for (int i = 0; i < 10; i++) {
                final String key = "key-" + i;
                final String value = "value-" + i;
                producer.send(new ProducerRecord<>(KAFKA_TOPIC_NAME, key, value));
            }
        }
    }
}
