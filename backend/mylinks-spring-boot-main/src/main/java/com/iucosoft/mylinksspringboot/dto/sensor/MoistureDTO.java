package com.iucosoft.mylinksspringboot.dto.sensor;

import lombok.Data;
import java.util.Date;

@Data
public class MoistureDTO {

    private Long deviceId;

    private Float moisture_val;

    private Date time_stamp;

}
