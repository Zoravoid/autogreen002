package com.iucosoft.mylinksspringboot.controllers.impl;

import com.iucosoft.mylinksspringboot.controllers.AbstractExceptionHandler;
import com.iucosoft.mylinksspringboot.controllers.HeatApi;
import com.iucosoft.mylinksspringboot.dto.sensor.HeatDTO;
import com.iucosoft.mylinksspringboot.entities.Heat;
import com.iucosoft.mylinksspringboot.mappers.HeatMapper;
import com.iucosoft.mylinksspringboot.service.HeatService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeatApiImpl extends AbstractExceptionHandler implements HeatApi{

    private final HeatService heatService;
    private final HeatMapper heatMapper;

    public HeatApiImpl(
            HeatService heatService,
            HeatMapper heatMapper
    ) {
        this.heatMapper = heatMapper;
        this.heatService = heatService;
    }


    @Override
    public ResponseEntity<Page<HeatDTO>> getHeatByDeviceId(Long deviceId, final Pageable pageable){
        final Page<HeatDTO> heats = heatService.getHeatByDeviceId(deviceId, pageable).map(heat -> heatMapper.toDto(heat));
        return ResponseEntity.ok().body(heats);
    }


    @Override
    public ResponseEntity<Page<HeatDTO>> getListOfAllHeatValues(Pageable pageable) {

        Page<Heat> pageHeat = heatService.findAllPaginated(pageable);
        return ResponseEntity.ok().body(pageHeat.map(heatMapper::toDto));
    }

}
