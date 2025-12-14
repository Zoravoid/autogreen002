package com.iucosoft.mylinksspringboot.service.impl;

import com.iucosoft.mylinksspringboot.dto.sensor.LightDTO;
import com.iucosoft.mylinksspringboot.entities.Light;
import com.iucosoft.mylinksspringboot.exceptions.BadRequestException;
import com.iucosoft.mylinksspringboot.exceptions.ResourceNotFoundException;
import com.iucosoft.mylinksspringboot.mappers.LightMapper;
import com.iucosoft.mylinksspringboot.repositories.LightRepository;
import com.iucosoft.mylinksspringboot.service.LightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LightServiceImpl extends AbstractServiceImpl<Light, Long> implements LightService {

    private final LightRepository lightRepository;
    private final LightMapper lightMapper;

    @Autowired
    public LightServiceImpl(LightRepository lightRepository, LightMapper lightMapper) {
        this.lightRepository = lightRepository;
        this.lightMapper = lightMapper;
    }

    @Override
    protected JpaRepository<Light, Long> getRepository() { return lightRepository; }

    @Override
    public Page<Light> getLightByDeviceId(Long deviceId, Pageable pageable) {
        return lightRepository.getLightByDeviceId(deviceId, pageable);
    }

    @Override
    public LightDTO listLightValues(LightDTO lightDTO, Long deviceId) {
        if (Objects.isNull(deviceId)) {
            throw new BadRequestException("The device_id must not be null");
        }
        Light light = lightRepository.findById(deviceId)
                        .orElseThrow(() -> new ResourceNotFoundException("Could not update the values"));

        light.setDeviceId(lightDTO.getDeviceId());
        light.setLightVal(lightDTO.getLightVal());
        light.setTimeStamp(lightDTO.getTimeStamp());
        Light listedLightValues = lightRepository.save(light);

        return lightMapper.toDto(listedLightValues);
    }

}
