package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.sensor.MoistureDTO;
import com.iucosoft.mylinksspringboot.entities.Moisture;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-29T23:07:00+0200",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 17.0.18 (Ubuntu)"
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
