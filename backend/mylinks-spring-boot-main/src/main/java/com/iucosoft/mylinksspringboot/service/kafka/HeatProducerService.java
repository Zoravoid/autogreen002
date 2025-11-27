package com.iucosoft.mylinksspringboot.service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iucosoft.mylinksspringboot.dto.sensor.HeatDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class HeatProducerService {

    public final KafkaTemplate<String, String> kafkaTemplate;
    public final ObjectMapper objectMapper;

    public HeatProducerService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendHeat(HeatDTO dto) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(dto);
            kafkaTemplate.send("b_heat", jsonMessage);
            System.out.println("Sent heat data to b_heat: " + jsonMessage);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
