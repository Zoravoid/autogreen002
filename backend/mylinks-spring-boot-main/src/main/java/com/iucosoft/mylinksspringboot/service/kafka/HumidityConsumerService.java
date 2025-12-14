package com.iucosoft.mylinksspringboot.service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iucosoft.mylinksspringboot.dto.sensor.HumidityDTO;
import com.iucosoft.mylinksspringboot.entities.Humidity;
import com.iucosoft.mylinksspringboot.mappers.HumidityMapper;
import com.iucosoft.mylinksspringboot.repositories.HumidityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class HumidityConsumerService {

    public final HumidityRepository humidityRepository;
    public final HumidityMapper humidityMapper;
    public final ObjectMapper objectMapper;

    @Autowired
    public HumidityConsumerService(HumidityRepository humidityRepository, HumidityMapper humidityMapper) {
        this.humidityRepository = humidityRepository;
        this.humidityMapper = humidityMapper;
        this.objectMapper = new ObjectMapper();
    }

    @Transactional
    @KafkaListener(topics = "s_humidity", groupId = "javaSpring")
    public void consumer(String message) {
        if (message == null || message.trim().isEmpty()) {
            System.out.println("Received empty message, skipping...");
            return;
        }
        try {
            HumidityDTO dto = objectMapper.readValue(message, HumidityDTO.class);

            Humidity humidity = humidityMapper.toEntity(dto);

            humidityRepository.save(humidity);

            System.out.println("Humidity saved: " + dto);
        }
        catch (Exception e) {
            System.err.println("Failed to process moisture message: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
