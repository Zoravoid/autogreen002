package com.iucosoft.mylinksspringboot.dto.sensor;

import lombok.Data;
import java.util.Date;

@Data
public class HeatDTO {

    private Long device_id;

    private Float heat_val;

    private Date time_stamp;

}