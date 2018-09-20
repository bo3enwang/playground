package com.bow3n.learn.kafka.listener;

import com.bow3n.learn.kafka.model.User;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * @author bowen
 */
public class KafkaConsumer {
    @KafkaListener(topics = "Kafka_Example", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }


    @KafkaListener(topics = "Kafka_Example_json", groupId = "group_json",
            containerFactory = "userKafkaListenerFactory")
    public void consumeJson(User user) {
        System.out.println("Consumed JSON Message: " + user);
    }
}
