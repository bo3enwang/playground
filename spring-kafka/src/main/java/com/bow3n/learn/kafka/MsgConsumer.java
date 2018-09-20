package com.bow3n.learn.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author bowen
 */
@Component
public class MsgConsumer {
    @KafkaListener(topics = {"test"})
    public void processMessage(String content) {

        System.out.println("消息被消费"+content);
    }

}
