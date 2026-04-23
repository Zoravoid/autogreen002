package com.iucosoft.mylinksspringboot.service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iucosoft.mylinksspringboot.dto.calculia.PlayerDTO;
import com.iucosoft.mylinksspringboot.entities.Player;
import com.iucosoft.mylinksspringboot.mappers.PlayerMapper;
import com.iucosoft.mylinksspringboot.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PlayerConsumerService {

    public final PlayerMapper playerMapper;
    public final PlayerRepository playerRepository;
    public final ObjectMapper objectMapper;
    public final PlayerProducerService playerProducerService;

    @Autowired
    public PlayerConsumerService(PlayerRepository playerRepository, PlayerMapper playerMapper, PlayerProducerService playerProducerService) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
        this.objectMapper = new ObjectMapper();
        this.playerProducerService = playerProducerService;
    }

    @Transactional
    @KafkaListener(topics = "s_player_data", groupId = "javaSpring")
    public void consume(String message) {
        if (message == null || message.trim().isEmpty()) {
            System.out.println("Received empty message, skipping...");
            return;
        }
        try {
            PlayerDTO dto = objectMapper.readValue(message, PlayerDTO.class);

            Player player = playerMapper.toEntity(dto);

            playerRepository.save(player);

            System.out.println("Player saved: " + dto);

            playerProducerService.sendPlayer(dto);
        }
        catch (Exception e) {
            System.err.println("Failed to process moisture message: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
