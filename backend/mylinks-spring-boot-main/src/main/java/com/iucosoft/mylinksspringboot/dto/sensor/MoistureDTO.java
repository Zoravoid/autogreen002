package com.iucosoft.mylinksspringboot.dto.sensor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;

@Data
public class MoistureDTO {

    @JsonProperty("device_id")
    private Long deviceId;

    @JsonProperty("moisture_val")
    private Float moisture_val;

    @JsonProperty("time_stamp")
    private Date time_stamp;

}
