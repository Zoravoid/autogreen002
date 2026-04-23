package com.iucosoft.mylinksspringboot.dto.calculia;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PlayerDTO {

    @JsonProperty("player_id")
    private Long playerId;

    @JsonProperty("player")
    private String player;

    @JsonProperty("level")
    private Long level;

    @JsonProperty("difficulty1")
    private Float difficulty1;

    @JsonProperty("difficulty2")
    private Float difficulty2;

    @JsonProperty("difficulty3")
    private Float difficulty3;

    @JsonProperty("difficulty4")
    private Float difficulty4;

    @JsonProperty("difficulty5")
    private Float difficulty5;

    @JsonProperty("difficulty6")
    private Float difficulty6;

    @JsonProperty("difficulty7")
    private Float difficulty7;

    @JsonProperty("prediction_probability")
    private Float predictionProbability;

    @JsonProperty("result")
    private Float result;

    @JsonProperty("number_sense")
    private Float numberSense;

    @JsonProperty("counting")
    private Float counting;

    @JsonProperty("arithmetic")
    private Float arithmetic;

    @JsonProperty("visual_patterns")
    private Float visualPatterns;

    @JsonProperty("memory")
    private Float memory;

    @JsonProperty("stars")
    private Float stars;

    @JsonProperty("time_stamp")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timeStamp;

}
