package com.iucosoft.mylinksspringboot.dto.sensor;

import lombok.Data;
import java.util.Date;

@Data
public class HumidityDTO {

    private Long deviceId;

    private Float humidity_val;

    private Date time_stamp;

}
