package com.iucosoft.mylinksspringboot.service;

import com.iucosoft.mylinksspringboot.dto.sensor.MoistureDTO;
import com.iucosoft.mylinksspringboot.entities.Moisture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface MoistureService extends OperationIntf<Moisture, Long> {

    MoistureDTO listMoistureValues(MoistureDTO moistureDTO, Long deviceId);

    Page<Moisture> getMoistureByDeviceId(final Long deviceId, final Pageable pageable);

}
