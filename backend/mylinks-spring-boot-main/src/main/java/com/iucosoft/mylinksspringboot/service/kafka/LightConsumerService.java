package com.iucosoft.mylinksspringboot.service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iucosoft.mylinksspringboot.dto.sensor.LightDTO;
import com.iucosoft.mylinksspringboot.entities.Light;
import com.iucosoft.mylinksspringboot.mappers.LightMapper;
import com.iucosoft.mylinksspringboot.repositories.LightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LightConsumerService {

    public final LightMapper lightMapper;
    public final LightRepository lightRepository;
    public final ObjectMapper objectMapper;
    public final LightProducerService lightProducerService;

    @Autowired
    public LightConsumerService(LightRepository lightRepository, LightMapper lightMapper, LightProducerService lightProducerService) {
        this.lightRepository = lightRepository;
        this.lightMapper = lightMapper;
        this.lightProducerService = lightProducerService;
        this.objectMapper = new ObjectMapper();
    }

    @Transactional
    @KafkaListener(topics = "s_light", groupId = "javaSpring")
    public void consume(String message) {
        if (message == null || message.trim().isEmpty()) {
            System.out.println("Received empty message, skipping...");
            return;
        }
        try {
            LightDTO dto = objectMapper.readValue(message, LightDTO.class);

            Light light = lightMapper.toEntity(dto);

            lightRepository.save(light);

            System.out.println("Light save: " + dto);

            lightProducerService.sendLight(dto);
        }
        catch (Exception e) {
            System.err.println("Failed to process moisture message: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
