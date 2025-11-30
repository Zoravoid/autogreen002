package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.sensor.HumidityDTO;
import com.iucosoft.mylinksspringboot.entities.Humidity;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-01T00:29:14+0100",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 1.8.0_472 (Private Build)"
)
@Component
public class HumidityMapperImpl implements HumidityMapper {

    @Override
    public HumidityDTO toDto(Humidity humidity) {
        if ( humidity == null ) {
            return null;
        }

        HumidityDTO humidityDTO = new HumidityDTO();

        humidityDTO.setDeviceId( humidity.getDeviceId() );
        humidityDTO.setHumidityVal( humidity.getHumidityVal() );
        humidityDTO.setTimeStamp( humidity.getTimeStamp() );

        return humidityDTO;
    }

    @Override
    public Humidity toEntity(HumidityDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Humidity.HumidityBuilder<?, ?> humidity = Humidity.builder();

        humidity.deviceId( dto.getDeviceId() );
        humidity.humidityVal( dto.getHumidityVal() );
        humidity.timeStamp( dto.getTimeStamp() );

        return humidity.build();
    }
}
