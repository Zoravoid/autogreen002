package com.iucosoft.mylinksspringboot.service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iucosoft.mylinksspringboot.dto.sensor.LightDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

//@Component
public class LightProducerService {

    public final KafkaTemplate<String, String> kafkaTemplate;
    public final ObjectMapper objectMapper;

    public LightProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public void sendLight(LightDTO dto) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(dto);
            kafkaTemplate.send("b_light", jsonMessage);
            System.out.println("Send light data to b_light: " + jsonMessage);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
