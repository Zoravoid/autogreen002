package com.iucosoft.mylinksspringboot.service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iucosoft.mylinksspringboot.dto.sensor.Co2DTO;
import com.iucosoft.mylinksspringboot.entities.Co2;
import com.iucosoft.mylinksspringboot.mappers.Co2Mapper;
import com.iucosoft.mylinksspringboot.repositories.Co2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//@Component
public class Co2ConsumerService {

    public final Co2Mapper co2Mapper;
    public final Co2Repository co2Repository;
    public final ObjectMapper objectMapper;
    public final Co2ProducerService co2ProducerService;

    //@Autowired
    public Co2ConsumerService(Co2Repository co2Repository, Co2Mapper co2Mapper, Co2ProducerService co2ProducerService) {
        this.co2Repository = co2Repository;
        this.co2Mapper = co2Mapper;
        this.objectMapper = new ObjectMapper();
        this.co2ProducerService = co2ProducerService;
    }

    //@Transactional
    //@KafkaListener(topics = "s_co2", groupId = "javaSpring")
    public void consume(String message) {
        if (message == null || message.trim().isEmpty()) {
            System.out.println("Received empty message, skipping...");
            return;
        }
        try {
            Co2DTO dto = objectMapper.readValue(message, Co2DTO.class);

            Co2 co2 = co2Mapper.toEntity(dto);

            co2Repository.save(co2);

            System.out.println("Co2 saved: " + dto);

            co2ProducerService.sendCo2(dto);
        }
        catch (Exception e) {
            System.err.println("Failed to process moisture message: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
