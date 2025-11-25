package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.sensor.HeatDTO;
import com.iucosoft.mylinksspringboot.entities.Heat;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-25T20:59:41+0100",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 1.8.0_462 (Temurin)"
)
@Component
public class HeatMapperImpl implements HeatMapper {

    @Override
    public HeatDTO toDto(Heat heat) {
        if ( heat == null ) {
            return null;
        }

        HeatDTO heatDTO = new HeatDTO();

        heatDTO.setDeviceId( heat.getDeviceId() );
        heatDTO.setHeat_val( heat.getHeat_val() );
        heatDTO.setTime_stamp( heat.getTime_stamp() );

        return heatDTO;
    }

    @Override
    public Heat toEntity(HeatDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Heat.HeatBuilder<?, ?> heat = Heat.builder();

        heat.deviceId( dto.getDeviceId() );
        heat.heat_val( dto.getHeat_val() );
        heat.time_stamp( dto.getTime_stamp() );

        return heat.build();
    }
}
