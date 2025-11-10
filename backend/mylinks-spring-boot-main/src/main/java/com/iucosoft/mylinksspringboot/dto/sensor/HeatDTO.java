package com.iucosoft.mylinksspringboot.dto.sensor;

import lombok.Data;
import java.util.Date;

@Data
public class HeatDTO {

    private Long device_id;

    private Float heat_val;

    private Date time_stamp;

    public HeatDTO(Long device_id, Float heat_val, Date time_stamp){
        this.device_id = device_id;
        this.heat_val = heat_val;
        this.time_stamp = time_stamp;
    }

}