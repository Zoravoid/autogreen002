package com.iucosoft.mylinksspringboot.service;

import com.iucosoft.mylinksspringboot.dto.sensor.HeatDTO;
import com.iucosoft.mylinksspringboot.entities.Heat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface HeatService extends OperationIntf<Heat, Long> {

    HeatDTO listHeatValues(HeatDTO heatDTO, Long deviceId);

    Page<Heat> getHeatByDeviceId(final Long deviceId, final Pageable pageable);

}
