package com.iucosoft.mylinksspringboot.service.impl;

import com.iucosoft.mylinksspringboot.dto.calculia.PlayerCreateDTO;
import com.iucosoft.mylinksspringboot.dto.calculia.PlayerDTO;
import com.iucosoft.mylinksspringboot.entities.Player;
import com.iucosoft.mylinksspringboot.exceptions.BadRequestException;
import com.iucosoft.mylinksspringboot.exceptions.ResourceNotFoundException;
import com.iucosoft.mylinksspringboot.mappers.PlayerMapper;
import com.iucosoft.mylinksspringboot.repositories.PlayerRepository;
import com.iucosoft.mylinksspringboot.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PlayerServiceImpl extends AbstractServiceImpl<Player, Long> implements PlayerService{

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, PlayerMapper playerMapper){
        this.playerMapper = playerMapper;
        this.playerRepository = playerRepository;
    }

    @Override
    protected JpaRepository<Player, Long> getRepository() { return playerRepository; }

    @Override
    public Page<Player> getPlayerByPlayerId(Long playerId, Pageable pageable) {
        return playerRepository.getPlayerByPlayerId(playerId, pageable);
    }

    @Override
    public Page<Player> getLevelByLevelId(Long level, Pageable pageable) {
        return playerRepository.getLevelByLevelId(level, pageable);
    }

    @Override
    public PlayerDTO listPlayerValues(PlayerDTO playerDTO, Long playerId){
        if (Objects.isNull(playerId)) {
            throw new BadRequestException("The player_id must not be null");
        }
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not update the values"));

        player.setPlayerId(playerDTO.getPlayerId());
        player.setPlayer(playerDTO.getPlayer());
        player.setLevel(playerDTO.getLevel());
        player.setDifficulty1(playerDTO.getDifficulty1());
        player.setDifficulty2(playerDTO.getDifficulty2());
        player.setDifficulty3(playerDTO.getDifficulty3());
        player.setDifficulty4(playerDTO.getDifficulty4());
        player.setDifficulty5(playerDTO.getDifficulty5());
        player.setDifficulty6(playerDTO.getDifficulty6());
        player.setDifficulty7(playerDTO.getDifficulty7());
        player.setPredictionProbability(playerDTO.getPredictionProbability());
        player.setResult(playerDTO.getResult());
        player.setNumberSense(playerDTO.getNumberSense());
        player.setCounting(playerDTO.getCounting());
        player.setArithmetic(playerDTO.getArithmetic());
        player.setVisualPatterns(playerDTO.getVisualPatterns());
        player.setMemory(playerDTO.getMemory());
        player.setStars(playerDTO.getStars());
        Player listedPlayerValues = playerRepository.save(player);

        return playerMapper.toDto(listedPlayerValues);
    }

}
