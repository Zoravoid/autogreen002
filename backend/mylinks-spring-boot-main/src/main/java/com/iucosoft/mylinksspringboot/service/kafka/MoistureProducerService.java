package com.iucosoft.mylinksspringboot.service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iucosoft.mylinksspringboot.dto.sensor.MoistureDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MoistureProducerService {

    public final KafkaTemplate<String, String> kafkaTemplate;
    public final ObjectMapper objectMapper;

    public MoistureProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public void sendMoisture(MoistureDTO dto) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(dto);
            kafkaTemplate.send("b_moisture", jsonMessage);
            System.out.println("Sent moisture data to b_moisture: " + jsonMessage);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
