package com.iucosoft.mylinksspringboot.controllers.impl;

import com.iucosoft.mylinksspringboot.controllers.AbstractExceptionHandler;
import com.iucosoft.mylinksspringboot.controllers.PlayerApi;
import com.iucosoft.mylinksspringboot.dto.calculia.PlayerCreateDTO;
import com.iucosoft.mylinksspringboot.dto.calculia.PlayerDTO;
import com.iucosoft.mylinksspringboot.entities.Player;
import com.iucosoft.mylinksspringboot.mappers.PlayerMapper;
import com.iucosoft.mylinksspringboot.mappers.custom.CustomPlayerMapper;
import com.iucosoft.mylinksspringboot.service.PlayerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerApiImpl extends AbstractExceptionHandler implements PlayerApi{

    private final PlayerService playerService;
    private final PlayerMapper playerMapper;
    private final CustomPlayerMapper customPlayerMapper;

    public PlayerApiImpl(PlayerService playerService, PlayerMapper playerMapper, CustomPlayerMapper customPlayerMapper) {
        this.playerMapper = playerMapper;
        this.playerService = playerService;
        this.customPlayerMapper = customPlayerMapper;
    }


    @Override
    public ResponseEntity<Page<PlayerDTO>> getPlayerByPlayerId(Long playerId, final Pageable pageable){
        final Page<PlayerDTO> players = playerService.getPlayerByPlayerId(playerId, pageable).map(player -> playerMapper.toDto(player));
        return ResponseEntity.ok().body(players);
    }

    @Override
    public  ResponseEntity<Page<PlayerDTO>> getLevelByLevelId(Long level, final Pageable pageable){
        final Page<PlayerDTO> levels = playerService.getLevelByLevelId(level, pageable).map(levelId -> playerMapper.toDto(levelId));
        return ResponseEntity.ok().body(levels);
    }

    @Override
    public ResponseEntity<Page<PlayerDTO>> getListOfAllPlayerValues(Pageable pageable) {

        Page<Player> pagePlayer = playerService.findAllPaginated(pageable);
        return ResponseEntity.ok().body(pagePlayer.map(playerMapper::toDto));
    }

    @Override
    public ResponseEntity<PlayerDTO> createPlayer(PlayerCreateDTO playerCreateDTO) {
        final Player playerToSave = Player.builder().playerId(playerCreateDTO.getPlayerId())
                .player(playerCreateDTO.getPlayer())
                .level(playerCreateDTO.getLevel())
                .difficulty1(playerCreateDTO.getDifficulty1())
                .difficulty2(playerCreateDTO.getDifficulty2())
                .difficulty3(playerCreateDTO.getDifficulty3())
                .difficulty4(playerCreateDTO.getDifficulty4())
                .difficulty5(playerCreateDTO.getDifficulty5())
                .difficulty6(playerCreateDTO.getDifficulty6())
                .difficulty7(playerCreateDTO.getDifficulty7())
                .predictionProbability(playerCreateDTO.getPredictionProbability())
                .result(playerCreateDTO.getResult())
                .numberSense(playerCreateDTO.getNumberSense())
                .counting(playerCreateDTO.getCounting())
                .arithmetic(playerCreateDTO.getArithmetic())
                .visualPatterns(playerCreateDTO.getVisualPatterns())
                .memory(playerCreateDTO.getMemory())
                .stars(playerCreateDTO.getStars())
                .timeStamp(playerCreateDTO.getTimeStamp()).build();
        final Player savedPlayer = playerService.save(playerToSave);
        final PlayerDTO savedPlayerDTO = customPlayerMapper.toDto(savedPlayer);
        return ResponseEntity.ok(savedPlayerDTO);
    }

}
