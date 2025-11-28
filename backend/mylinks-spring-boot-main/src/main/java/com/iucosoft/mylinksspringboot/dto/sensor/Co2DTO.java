package com.iucosoft.mylinksspringboot.dto.sensor;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;

@Data
public class Co2DTO {

    @JsonProperty("device_id")
    private Long deviceId;

    @JsonProperty("co2_val")
    private Float co2Val;

    @JsonProperty("time_stamp")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timeStamp;

}
