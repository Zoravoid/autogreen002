package com.iucosoft.mylinksspringboot.service.impl;

import com.iucosoft.mylinksspringboot.dto.sensor.Co2DTO;
import com.iucosoft.mylinksspringboot.entities.Co2;
import com.iucosoft.mylinksspringboot.exceptions.BadRequestException;
import com.iucosoft.mylinksspringboot.exceptions.ResourceNotFoundException;
import com.iucosoft.mylinksspringboot.mappers.Co2Mapper;
import com.iucosoft.mylinksspringboot.repositories.Co2Repository;
import com.iucosoft.mylinksspringboot.service.Co2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class Co2ServiceImpl extends AbstractServiceImpl<Co2, Long> implements Co2Service {

    private final Co2Repository co2Repository;
    private final Co2Mapper co2Mapper;

    @Autowired
    public Co2ServiceImpl(Co2Repository co2Repository, Co2Mapper co2Mapper) {
        this.co2Mapper = co2Mapper;
        this.co2Repository = co2Repository;
    }

    @Override
    protected JpaRepository<Co2, Long> getRepository() {return co2Repository;}

    @Override
    public Page<Co2> getCo2ByDeviceId(Long deviceId, Pageable pageable){
        return co2Repository.getCo2ByDeviceId(deviceId, pageable);
    }

    @Override
    public Co2DTO listCo2Values(Co2DTO co2DTO, Long deviceId){
        if (Objects.isNull(deviceId)) {
            throw new BadRequestException("The device_id must not be null");
        }
        Co2 co2 = co2Repository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not update the values"));

        co2.setDeviceId(co2DTO.getDeviceId());
        co2.setCo2_val(co2DTO.getCo2_val());
        co2.setTime_stamp(co2.getTime_stamp());
        Co2 listedCo2Values = co2Repository.save(co2);

        return co2Mapper.toDto(listedCo2Values);
    }

}
