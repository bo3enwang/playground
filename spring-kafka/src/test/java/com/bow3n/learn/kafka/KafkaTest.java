package com.bow3n.learn.kafka;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author bowen
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
public class KafkaTest {

    @Autowired
    MsgProducer msgProducer;
    private Logger logger = LoggerFactory.getLogger(KafkaTest.class);

    @Test
    public void testSend() {
        msgProducer.sendMessage("test", "topic--------1");
        msgProducer.sendMessage("test", "topic--------2");
    }

}
