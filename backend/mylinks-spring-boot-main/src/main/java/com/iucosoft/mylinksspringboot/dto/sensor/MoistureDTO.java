package com.iucosoft.mylinksspringboot.dto.sensor;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;

@Data
public class MoistureDTO {

    @JsonProperty("device_id")
    private Long deviceId;

    @JsonProperty("moisture_val")
    private Float moistureVal;

    @JsonProperty("time_stamp")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timeStamp;

}
