package com.iucosoft.mylinksspringboot.dto.calculia;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerCreateDTO {
    @NotNull(message = "error.player.id.notnull")
    @NotBlank(message = "error.player.id.notblank")
    private Long playerId;

    private String player;

    private Long level;

    private Float difficulty1;

    private Float difficulty2;

    private Float difficulty3;

    private Float difficulty4;

    private Float difficulty5;

    private Float difficulty6;

    private Float difficulty7;

    private Float predictionProbability;

    private Float result;

    private Float numberSense;

    private Float counting;

    private Float arithmetic;

    private Float visualPatterns;

    private Float memory;

    private Float stars;

    private Date timeStamp;
}
