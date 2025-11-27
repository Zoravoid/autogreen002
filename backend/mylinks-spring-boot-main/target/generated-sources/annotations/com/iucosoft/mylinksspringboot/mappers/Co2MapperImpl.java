package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.sensor.Co2DTO;
import com.iucosoft.mylinksspringboot.entities.Co2;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-27T19:20:19+0100",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 1.8.0_462 (Temurin)"
)
@Component
public class Co2MapperImpl implements Co2Mapper {

    @Override
    public Co2DTO toDto(Co2 co2) {
        if ( co2 == null ) {
            return null;
        }

        Co2DTO co2DTO = new Co2DTO();

        co2DTO.setDeviceId( co2.getDeviceId() );
        co2DTO.setCo2Val( co2.getCo2Val() );
        co2DTO.setTimeStamp( co2.getTimeStamp() );

        return co2DTO;
    }

    @Override
    public Co2 toEntity(Co2DTO dto) {
        if ( dto == null ) {
            return null;
        }

        Co2.Co2Builder<?, ?> co2 = Co2.builder();

        co2.deviceId( dto.getDeviceId() );
        co2.co2Val( dto.getCo2Val() );
        co2.timeStamp( dto.getTimeStamp() );

        return co2.build();
    }
}
