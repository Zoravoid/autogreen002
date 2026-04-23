package com.iucosoft.mylinksspringboot.repositories;

import com.iucosoft.mylinksspringboot.entities.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query("Select p FROM Player p WHERE p.playerId = :playerId")
    Page<Player> getPlayerByPlayerId(final @Param("playerId") Long playerId, Pageable pageable);

    @Query("Select l FROM Player l WHERE l.level = :level")
    Page<Player> getLevelByLevelId(final @Param("level") Long level, Pageable pageable);
}
