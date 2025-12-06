package com.iucosoft.mylinksspringboot.controllers.impl;

import com.iucosoft.mylinksspringboot.controllers.AbstractExceptionHandler;
import com.iucosoft.mylinksspringboot.controllers.LightApi;
import com.iucosoft.mylinksspringboot.dto.sensor.LightDTO;
import com.iucosoft.mylinksspringboot.entities.Light;
import com.iucosoft.mylinksspringboot.mappers.LightMapper;
import com.iucosoft.mylinksspringboot.service.LightService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LightApiImpl extends AbstractExceptionHandler implements LightApi {

    private final LightService lightService;
    private final LightMapper lightMapper;

    public LightApiImpl(LightService lightService, LightMapper lightMapper) {
        this.lightService = lightService;
        this.lightMapper = lightMapper;
    }

    @Override
    public ResponseEntity<Page<LightDTO>> getLightByDeviceId(Long deviceId, final Pageable pageable) {
        final Page<LightDTO> lights = lightService.getLightByDeviceId(deviceId, pageable).map(light -> lightMapper.toDto(light));
        return ResponseEntity.ok().body(lights);
    }

    @Override
    public ResponseEntity<Page<LightDTO>> getListOfAllLightValues(Pageable pageable) {

        Page<Light> pageLight = lightService.findAllPaginated(pageable);
        return ResponseEntity.ok().body(pageLight.map(lightMapper::toDto));
    }

}
