package com.iucosoft.mylinksspringboot.service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerService {

    @KafkaListener(topics = "s_moisture", groupId = "groupId")
    void listener(String data) {
        System.out.println("Listener recived: " + data);
    }

}