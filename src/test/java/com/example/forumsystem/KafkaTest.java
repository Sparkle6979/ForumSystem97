package com.example.forumsystem;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author sparkle6979l
 * @version 1.0
 * @data 2023/5/11 12:11
 */
@SpringBootTest
public class KafkaTest {
    @Autowired
    private KafkaProducer kafkaProducer;
    @Test
    public void testKafka() throws InterruptedException {
        kafkaProducer.sendMessage("test","你好");
        kafkaProducer.sendMessage("test","在吗");
        Thread.sleep(1000*10);
    }
}

@Component
class KafkaProducer{
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendMessage(String topic,String content){
        kafkaTemplate.send(topic,content);
    }
}
@Component
class KafkaConsumer{
    @KafkaListener(topics = ("test"))
    public void handleMessage(ConsumerRecord record){
        System.out.println(record.value());
    }
}
