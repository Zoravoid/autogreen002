package com.iucosoft.mylinksspringboot.service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iucosoft.mylinksspringboot.dto.calculia.PlayerDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

//@Component
public class PlayerProducerService {

    public final KafkaTemplate<String, String> kafkaTemplate;
    public final ObjectMapper objectMapper;

    public PlayerProducerService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper){
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendPlayer(PlayerDTO dto){
        try {
            String jsonMessage = objectMapper.writeValueAsString(dto);
            kafkaTemplate.send("b_player_data", jsonMessage);
            System.out.println("Sent Player data to b_player" + jsonMessage);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
