package com.iucosoft.mylinksspringboot.mappers.custom;

import com.iucosoft.mylinksspringboot.dto.calculia.PlayerCreateDTO;
import com.iucosoft.mylinksspringboot.dto.calculia.PlayerDTO;
import com.iucosoft.mylinksspringboot.entities.Player;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomPlayerMapper {
    PlayerDTO toDto(Player player);

    PlayerCreateDTO toCreateDto(Player player);
}
