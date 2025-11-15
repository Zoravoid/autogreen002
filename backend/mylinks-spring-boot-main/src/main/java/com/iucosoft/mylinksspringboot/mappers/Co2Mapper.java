package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.sensor.Co2DTO;
import com.iucosoft.mylinksspringboot.entities.Co2;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Co2Mapper {

    Co2DTO toDto(Co2 co2);

    Co2 toEntity(Co2DTO dto);

}
