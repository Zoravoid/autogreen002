package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.calculia.PlayerDTO;
import com.iucosoft.mylinksspringboot.entities.Player;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    PlayerDTO toDto(Player player);

    Player toEntity(PlayerDTO dto);

}
