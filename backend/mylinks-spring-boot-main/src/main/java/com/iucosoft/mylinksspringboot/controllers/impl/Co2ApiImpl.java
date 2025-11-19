package com.iucosoft.mylinksspringboot.controllers.impl;

import com.iucosoft.mylinksspringboot.controllers.AbstractExceptionHandler;
import com.iucosoft.mylinksspringboot.controllers.Co2Api;
import com.iucosoft.mylinksspringboot.dto.sensor.Co2DTO;
import com.iucosoft.mylinksspringboot.entities.Co2;
import com.iucosoft.mylinksspringboot.mappers.Co2Mapper;
import com.iucosoft.mylinksspringboot.service.Co2Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Co2ApiImpl extends AbstractExceptionHandler implements Co2Api {

    private final Co2Service co2Service;
    private final Co2Mapper co2Mapper;

    public Co2ApiImpl(Co2Service co2Service, Co2Mapper co2Mapper) {
        this.co2Service = co2Service;
        this.co2Mapper = co2Mapper;
    }

    @Override
    public ResponseEntity<Page<Co2DTO>> getCo2ByDeviceId(Long deviceId, final Pageable pageable) {
        final Page<Co2DTO> co2s = co2Service.getCo2ByDeviceId(deviceId, pageable).map(co2 -> co2Mapper.toDto(co2));
        return ResponseEntity.ok().body(co2s);
    }

    @Override
    public ResponseEntity<Page<Co2DTO>> getListOfAllCo2Values(Pageable pageable) {

        Page<Co2> pageco2 = co2Service.findAllPaginated(pageable);
        return ResponseEntity.ok().body(pageco2.map(co2Mapper::toDto));
    }

}
