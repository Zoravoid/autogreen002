package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.sensor.MoistureDTO;
import com.iucosoft.mylinksspringboot.entities.Moisture;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-28T14:38:20+0100",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 1.8.0_472 (Private Build)"
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
        moistureDTO.setMoistureVal( moisture.getMoistureVal() );
        moistureDTO.setTimeStamp( moisture.getTimeStamp() );

        return moistureDTO;
    }

    @Override
    public Moisture toEntity(MoistureDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Moisture.MoistureBuilder<?, ?> moisture = Moisture.builder();

        moisture.deviceId( dto.getDeviceId() );
        moisture.moistureVal( dto.getMoistureVal() );
        moisture.timeStamp( dto.getTimeStamp() );

        return moisture.build();
    }
}
