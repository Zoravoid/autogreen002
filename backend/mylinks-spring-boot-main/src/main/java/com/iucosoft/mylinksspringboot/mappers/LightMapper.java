package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.sensor.LightDTO;
import com.iucosoft.mylinksspringboot.entities.Light;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LightMapper {

    LightDTO toDto(Light light);

    Light toEntity(LightDTO dto);

}
