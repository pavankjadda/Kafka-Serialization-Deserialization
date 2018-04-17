package com.kafkaserialization.service;

import com.kafkaserialization.model.Order;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Arrays;
import java.util.Properties;

public class ReceiveEvent
{
    public static void main(String[] args)
    {
        Properties properties=new Properties();
        properties.put("application.id", "cqrs-streams");
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("acks", "all");
        properties.put("group.id","orders_group");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.serializer", "com.kafkaserialization.serializers.OrderSerializer");
        properties.put("value.deserializer", "com.kafkaserialization.serializers.OrderDeserializer");
        Consumer<String, Order> kafkaConsumer=new KafkaConsumer<String, Order>(properties);

        kafkaConsumer.subscribe(Arrays.asList("order"));
        while (true)
        {
            ConsumerRecords<String, Order> events = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, Order> event : events)
            {
                System.out.println("Event received " + event.value().orderId);
            }
        }
    }

}
