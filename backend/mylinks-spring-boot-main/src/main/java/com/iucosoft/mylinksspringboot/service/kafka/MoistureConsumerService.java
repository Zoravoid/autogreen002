package com.iucosoft.mylinksspringboot.service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iucosoft.mylinksspringboot.dto.sensor.MoistureDTO;
import com.iucosoft.mylinksspringboot.entities.Moisture;
import com.iucosoft.mylinksspringboot.mappers.MoistureMapper;
import com.iucosoft.mylinksspringboot.repositories.MoistureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MoistureConsumerService {

    public final MoistureMapper moistureMapper;
    public final MoistureRepository moistureRepository;
    public final ObjectMapper objectMapper;
    private final MoistureProducerService moistureProducerService;

    @Autowired
    public MoistureConsumerService(MoistureRepository moistureRepository, MoistureMapper moistureMapper, MoistureProducerService moistureProducerService) {
        this.moistureRepository = moistureRepository;
        this.moistureMapper = moistureMapper;
        this.objectMapper = new ObjectMapper();
        this.moistureProducerService = moistureProducerService;
    }

    @Transactional
    @KafkaListener(topics = "s_moisture", groupId = "groupId")
    public void consume(String message) {
        if (message == null || message.trim().isEmpty()) {
            System.out.println("Received empty message, skipping...");
            return;
        }
        try {
            MoistureDTO dto = objectMapper.readValue(message, MoistureDTO.class);

            Moisture moisture = moistureMapper.toEntity(dto);

            moistureRepository.save(moisture);

            System.out.println("Moisture saved: " + dto);

            moistureProducerService.sendMoisture(dto);
        }
        catch (Exception e) {
            System.err.println("Failed to process moisture message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
