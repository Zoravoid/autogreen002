package com.iucosoft.mylinksspringboot.controllers.impl;

import com.iucosoft.mylinksspringboot.controllers.AbstractExceptionHandler;
import com.iucosoft.mylinksspringboot.controllers.MoistureApi;
import com.iucosoft.mylinksspringboot.dto.MessageDTO;
import com.iucosoft.mylinksspringboot.dto.sensor.MoistureDTO;
import com.iucosoft.mylinksspringboot.entities.Moisture;
import com.iucosoft.mylinksspringboot.mappers.MoistureMapper;
import com.iucosoft.mylinksspringboot.service.MoistureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoistureApiImpl extends AbstractExceptionHandler implements MoistureApi {

    private final MoistureService moistureService;
    private final MoistureMapper moistureMapper;

    public MoistureApiImpl(MoistureService moistureService, MoistureMapper moistureMapper) {
        this.moistureService = moistureService;
        this.moistureMapper = moistureMapper;
    }

    @Override
    public ResponseEntity<Page<MoistureDTO>> getMoistureByDeviceId(Long deviceId, final Pageable pageable) {
        final Page<MoistureDTO> moistures = moistureService.getMoistureByDeviceId(deviceId, pageable).map(moisture -> moistureMapper.toDto(moisture));
        return ResponseEntity.ok().body(moistures);
    }

    @Override
    public ResponseEntity<Page<MoistureDTO>> getListOfAllMoistureValues(Pageable pageable) {

        Page<Moisture> pageMoisture = moistureService.findAllPaginated(pageable);
        return ResponseEntity.ok().body(pageMoisture.map(moistureMapper::toDto));
    }

}
