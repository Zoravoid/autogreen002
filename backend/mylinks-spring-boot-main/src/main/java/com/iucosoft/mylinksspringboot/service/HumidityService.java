package com.iucosoft.mylinksspringboot.service;

import com.iucosoft.mylinksspringboot.dto.sensor.HumidityDTO;
import com.iucosoft.mylinksspringboot.entities.Humidity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface HumidityService extends OperationIntf<Humidity, Long> {

    HumidityDTO listHumidityValues(HumidityDTO humidityDTO, Long deviceId);

    Page<Humidity> getHumidityByDeviceId(final Long deviceId, final Pageable pageable);

}
