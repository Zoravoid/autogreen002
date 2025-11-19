package com.iucosoft.mylinksspringboot.service;

import com.iucosoft.mylinksspringboot.dto.sensor.Co2DTO;
import com.iucosoft.mylinksspringboot.entities.Co2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface Co2Service extends OperationIntf<Co2, Long> {

    Co2DTO listCo2Values(Co2DTO co2DTO, Long deviceId);

    Page<Co2> getCo2ByDeviceId(final Long deviceId, final Pageable pageable);

}
