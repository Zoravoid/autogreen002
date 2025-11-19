package com.iucosoft.mylinksspringboot.dto.sensor;

import lombok.Data;
import java.util.Date;

@Data
public class Co2DTO {

    private Long deviceId;

    private Float co2_val;

    private Date time_stamp;

}
