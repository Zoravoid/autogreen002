package com.iucosoft.mylinksspringboot.service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iucosoft.mylinksspringboot.dto.sensor.HeatDTO;
import com.iucosoft.mylinksspringboot.entities.Heat;
import com.iucosoft.mylinksspringboot.mappers.HeatMapper;
import com.iucosoft.mylinksspringboot.repositories.HeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//@Component
public class HeatConsumerService {

    public final HeatMapper heatMapper;
    public final HeatRepository heatRepository;
    public final ObjectMapper objectMapper;
    public final HeatProducerService heatProducerService;

    //@Autowired
    public HeatConsumerService(HeatRepository heatRepository, HeatMapper heatMapper, HeatProducerService heatProducerService) {
        this.heatRepository = heatRepository;
        this.heatMapper = heatMapper;
        this.objectMapper = new ObjectMapper();
        this.heatProducerService = heatProducerService;
    }

    //@Transactional
    //@KafkaListener(topics = "s_heat", groupId = "javaSpring")
    public void consume(String message) {
        if (message == null || message.trim().isEmpty()) {
            System.out.println("Received empty message, skipping...");
            return;
        }
        try {
            HeatDTO dto = objectMapper.readValue(message, HeatDTO.class);

            Heat heat = heatMapper.toEntity(dto);

            heatRepository.save(heat);

            System.out.println("Heat saved: " + dto);

            heatProducerService.sendHeat(dto);
        }
        catch (Exception e) {
            System.err.println("Failed to process moisture message: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
