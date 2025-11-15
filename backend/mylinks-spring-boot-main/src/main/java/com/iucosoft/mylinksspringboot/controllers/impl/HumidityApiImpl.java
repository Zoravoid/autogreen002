package com.iucosoft.mylinksspringboot.controllers.impl;

import com.iucosoft.mylinksspringboot.controllers.AbstractExceptionHandler;
import com.iucosoft.mylinksspringboot.controllers.HumidityApi;
import com.iucosoft.mylinksspringboot.dto.sensor.HumidityDTO;
import com.iucosoft.mylinksspringboot.entities.Humidity;
import com.iucosoft.mylinksspringboot.mappers.HumidityMapper;
import com.iucosoft.mylinksspringboot.service.HumidityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HumidityApiImpl extends AbstractExceptionHandler implements HumidityApi {

    private final HumidityService humidityService;
    private final HumidityMapper humidityMapper;

    public HumidityApiImpl(HumidityService humidityService, HumidityMapper humidityMapper) {
        this.humidityService = humidityService;
        this.humidityMapper = humidityMapper;
    }

    @Override
    public ResponseEntity<Page<HumidityDTO>> getHumidityByDeviceId(Long deviceId, final Pageable pageable) {
        final Page<HumidityDTO> humidities = humidityService.getHumidityByDeviceId(deviceId, pageable).map(humidity -> humidityMapper.toDto(humidity));
        return ResponseEntity.ok().body(humidities);
    }

    @Override
    public ResponseEntity<Page<HumidityDTO>> getListOfAllHumidityValues(Pageable pageable) {

        Page<Humidity> pagehumidity = humidityService.findAllPaginated(pageable);
        return ResponseEntity.ok().body(pagehumidity.map(humidityMapper::toDto));
    }
}
