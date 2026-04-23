package com.iucosoft.mylinksspringboot.controllers.impl;

import com.iucosoft.mylinksspringboot.controllers.AbstractExceptionHandler;
import com.iucosoft.mylinksspringboot.controllers.PlayerApi;
import com.iucosoft.mylinksspringboot.dto.calculia.PlayerDTO;
import com.iucosoft.mylinksspringboot.entities.Player;
import com.iucosoft.mylinksspringboot.mappers.PlayerMapper;
import com.iucosoft.mylinksspringboot.service.PlayerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerApiImpl extends AbstractExceptionHandler implements PlayerApi{

    private final PlayerService playerService;
    private final PlayerMapper playerMapper;

    public PlayerApiImpl(PlayerService playerService, PlayerMapper playerMapper) {
        this.playerMapper = playerMapper;
        this.playerService = playerService;
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

}
