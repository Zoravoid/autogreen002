package com.iucosoft.mylinksspringboot.service;

import com.iucosoft.mylinksspringboot.dto.sensor.HeatDTO;
import com.iucosoft.mylinksspringboot.entities.Heat;

import java.util.Date;
import java.util.List;

public interface HeatService extends OperationIntf<Heat, Long> {

    HeatDTO listHeatValues(HeatDTO heatDTO, Long device_id);

}
