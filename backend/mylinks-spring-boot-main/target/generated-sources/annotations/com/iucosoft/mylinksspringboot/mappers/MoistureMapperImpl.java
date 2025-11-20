package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.sensor.MoistureDTO;
import com.iucosoft.mylinksspringboot.entities.Moisture;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-20T02:21:45+0100",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 1.8.0_462 (Private Build)"
)
@Component
public class MoistureMapperImpl implements MoistureMapper {

    @Override
    public MoistureDTO toDto(Moisture moisture) {
        if ( moisture == null ) {
            return null;
        }

        MoistureDTO moistureDTO = new MoistureDTO();

        moistureDTO.setDeviceId( moisture.getDeviceId() );
        moistureDTO.setMoisture_val( moisture.getMoisture_val() );
        moistureDTO.setTime_stamp( moisture.getTime_stamp() );

        return moistureDTO;
    }

    @Override
    public Moisture toEntity(MoistureDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Moisture.MoistureBuilder<?, ?> moisture = Moisture.builder();

        moisture.deviceId( dto.getDeviceId() );
        moisture.moisture_val( dto.getMoisture_val() );
        moisture.time_stamp( dto.getTime_stamp() );

        return moisture.build();
    }
}
