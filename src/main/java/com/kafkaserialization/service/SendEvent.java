package com.kafkaserialization.service;

import com.kafkaserialization.model.Order;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
@EnableAutoConfiguration
public class SendEvent
{
    private Producer<String, Order> kafkaProducer;

    private Properties properties;

    public SendEvent()
    {
        this.properties = new Properties();
        properties.put("application.id", "cqrs-streams");
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("acks", "all");
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
/*        properties.put("value.serializer", "com.kafkaserialization.serializers.OrderSerializer");
        properties.put("value.deserializer", "com.kafkaserialization.serializers.OrderDeserializer");*/
        properties.put("value.serializer", "com.kafkaserialization.serializers.KafkaJsonSerializer");
        properties.put("value.deserializer", "com.kafkaserialization.serializers.KafkaJsonDeserializer");

        kafkaProducer = new KafkaProducer<>(properties);
    }

    public void sendOrderEvent(Order order) throws ExecutionException, InterruptedException
    {
        ProducerRecord<String, Order> orderRecord = new ProducerRecord<>("order", order.getOrderId(), order);
        Future<RecordMetadata> future = kafkaProducer.send(orderRecord);
        System.out.println("Customer order sent. Order Id: " + order.getOrderId());
        System.out.println("Order future.get(): " + future.get());
    }

}
