package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.sensor.HumidityDTO;
import com.iucosoft.mylinksspringboot.entities.Humidity;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-25T20:59:41+0100",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 1.8.0_462 (Temurin)"
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
        humidityDTO.setHumidity_val( humidity.getHumidity_val() );
        humidityDTO.setTime_stamp( humidity.getTime_stamp() );

        return humidityDTO;
    }

    @Override
    public Humidity toEntity(HumidityDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Humidity.HumidityBuilder<?, ?> humidity = Humidity.builder();

        humidity.deviceId( dto.getDeviceId() );
        humidity.humidity_val( dto.getHumidity_val() );
        humidity.time_stamp( dto.getTime_stamp() );

        return humidity.build();
    }
}
