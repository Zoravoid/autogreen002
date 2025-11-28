package com.iucosoft.mylinksspringboot.service.impl;

import com.iucosoft.mylinksspringboot.dto.sensor.HeatDTO;
import com.iucosoft.mylinksspringboot.entities.Heat;
import com.iucosoft.mylinksspringboot.exceptions.BadRequestException;
import com.iucosoft.mylinksspringboot.exceptions.ResourceNotFoundException;
import com.iucosoft.mylinksspringboot.mappers.HeatMapper;
import com.iucosoft.mylinksspringboot.repositories.HeatRepository;
import com.iucosoft.mylinksspringboot.service.HeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class HeatServiceImpl extends AbstractServiceImpl<Heat, Long> implements HeatService{

    private final HeatRepository heatRepository;
    private final HeatMapper heatMapper;

    @Autowired
    public HeatServiceImpl(HeatRepository heatRepository, HeatMapper heatMapper){
        this.heatMapper = heatMapper;
        this.heatRepository = heatRepository;
    }

    @Override
    protected JpaRepository<Heat, Long> getRepository() {
        return heatRepository;
    }


    @Override
    public Page<Heat> getHeatByDeviceId(Long deviceId, Pageable pageable) {
        return heatRepository.getHeatByDeviceId(deviceId, pageable);
    }


    @Override
    public HeatDTO listHeatValues(HeatDTO heatDTO, Long deviceId){
        if (Objects.isNull(deviceId)) {
            throw new BadRequestException("The device_id must not be null");
        }
        Heat heat = heatRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not update the values"));

        heat.setDeviceId(heatDTO.getDeviceId());
        heat.setHeatVal(heatDTO.getHeatVal());
        heat.setTimeStamp(heatDTO.getTimeStamp());
        Heat listedHeatValues = heatRepository.save(heat);

        return heatMapper.toDto(listedHeatValues);
    }

}
