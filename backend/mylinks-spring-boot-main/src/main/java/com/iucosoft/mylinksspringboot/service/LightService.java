package com.iucosoft.mylinksspringboot.service;

import com.iucosoft.mylinksspringboot.dto.sensor.LightDTO;
import com.iucosoft.mylinksspringboot.entities.Light;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LightService extends OperationIntf<Light, Long>{

    LightDTO listLightValues(LightDTO lightDTO, Long deviceId);

    Page<Light> getLightByDeviceId(final Long deviceId, final Pageable pageable);

}
