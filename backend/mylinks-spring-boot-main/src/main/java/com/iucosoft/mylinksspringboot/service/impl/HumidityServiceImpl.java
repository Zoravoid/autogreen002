package com.iucosoft.mylinksspringboot.service.impl;

import com.iucosoft.mylinksspringboot.dto.sensor.HumidityDTO;
import com.iucosoft.mylinksspringboot.entities.Humidity;
import com.iucosoft.mylinksspringboot.exceptions.BadRequestException;
import com.iucosoft.mylinksspringboot.exceptions.ResourceNotFoundException;
import com.iucosoft.mylinksspringboot.mappers.HumidityMapper;
import com.iucosoft.mylinksspringboot.repositories.HumidityRepository;
import com.iucosoft.mylinksspringboot.service.HumidityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class HumidityServiceImpl extends AbstractServiceImpl<Humidity, Long> implements HumidityService {

    public final HumidityMapper humidityMapper;
    public final HumidityRepository humidityRepository;

    @Autowired
    public HumidityServiceImpl(HumidityRepository humidityRepository, HumidityMapper humidityMapper) {
        this.humidityMapper = humidityMapper;
        this.humidityRepository = humidityRepository;
    }

    @Override
    protected JpaRepository<Humidity, Long> getRepository() {return humidityRepository;}

    @Override
    public Page<Humidity> getHumidityByDeviceId(Long deviceId, Pageable pageable) {
        return humidityRepository.getHumidityByDeviceId(deviceId, pageable);
    }

    @Override
    public HumidityDTO listHumidityValues(HumidityDTO humidityDTO, Long deviceId) {
        if (Objects.isNull(deviceId)) {
            throw new BadRequestException("The device_id must not be null");
        }
        Humidity humidity = humidityRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not update the values"));

        humidity.setHumidityVal(humidityDTO.getHumidityVal());
        humidity.setDeviceId(humidityDTO.getDeviceId());
        humidity.setTimeStamp(humidityDTO.getTimeStamp());
        Humidity listedHumidityValues = humidityRepository.save(humidity);

        return humidityMapper.toDto(listedHumidityValues);
    }

}
