package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.sensor.HeatDTO;
import com.iucosoft.mylinksspringboot.entities.Heat;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-30T18:57:18+0100",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 1.8.0_472 (Private Build)"
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
        heatDTO.setHeatVal( heat.getHeatVal() );
        heatDTO.setTimeStamp( heat.getTimeStamp() );

        return heatDTO;
    }

    @Override
    public Heat toEntity(HeatDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Heat.HeatBuilder<?, ?> heat = Heat.builder();

        heat.deviceId( dto.getDeviceId() );
        heat.heatVal( dto.getHeatVal() );
        heat.timeStamp( dto.getTimeStamp() );

        return heat.build();
    }
}
