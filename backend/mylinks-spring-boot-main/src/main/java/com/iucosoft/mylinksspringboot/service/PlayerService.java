package com.iucosoft.mylinksspringboot.service;

import com.iucosoft.mylinksspringboot.dto.calculia.PlayerDTO;
import com.iucosoft.mylinksspringboot.entities.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlayerService extends OperationIntf<Player, Long> {

    PlayerDTO listPlayerValues(PlayerDTO playerDTO, Long playerId);

    Page<Player> getPlayerByPlayerId(final Long playerId, final Pageable pageable);

    Page<Player> getLevelByLevelId(final Long level, final Pageable pageable);

}
