package com.iucosoft.mylinksspringboot.service.impl;

import com.iucosoft.mylinksspringboot.dto.sensor.MoistureDTO;
import com.iucosoft.mylinksspringboot.entities.Moisture;
import com.iucosoft.mylinksspringboot.exceptions.BadRequestException;
import com.iucosoft.mylinksspringboot.exceptions.ResourceNotFoundException;
import com.iucosoft.mylinksspringboot.mappers.MoistureMapper;
import com.iucosoft.mylinksspringboot.repositories.MoistureRepository;
import com.iucosoft.mylinksspringboot.service.MoistureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MoistureServiceImpl extends AbstractServiceImpl<Moisture, Long> implements MoistureService {

    public final MoistureMapper moistureMapper;
    public final MoistureRepository moistureRepository;

    @Autowired
    public MoistureServiceImpl(MoistureRepository moistureRepository, MoistureMapper moistureMapper) {
        this.moistureRepository = moistureRepository;
        this.moistureMapper = moistureMapper;
    }

    @Override
    protected JpaRepository<Moisture, Long> getRepository() {return moistureRepository;}

    @Override
    public Page<Moisture> getMoistureByDeviceId(Long deviceId, Pageable pageable) {
        return moistureRepository.getMoistureByDeviceId(deviceId, pageable);
    }

    @Override
    public MoistureDTO listMoistureValues(MoistureDTO moistureDTO, Long deviceId) {
        if (Objects.isNull(deviceId)) {
            throw new BadRequestException("The device_id must not be null");
        }
        Moisture moisture = moistureRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not update the values"));

        moisture.setMoistureVal(moistureDTO.getMoistureVal());
        moisture.setDeviceId(moistureDTO.getDeviceId());
        moisture.setTimeStamp(moistureDTO.getTimeStamp());
        Moisture listedMoistureValues = moistureRepository.save(moisture);

        return moistureMapper.toDto(listedMoistureValues);
    }

}
