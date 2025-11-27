package com.iucosoft.mylinksspringboot.service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iucosoft.mylinksspringboot.dto.sensor.HumidityDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class HumidityProducerService {

    public final KafkaTemplate<String, String> kafkaTemplate;
    public final ObjectMapper objectMapper;

    public HumidityProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public void sendHumidity(HumidityDTO dto) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(dto);
            kafkaTemplate.send("b_humidity", jsonMessage);
            System.out.println("Sent humidity data to b_humidity: " + jsonMessage);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
