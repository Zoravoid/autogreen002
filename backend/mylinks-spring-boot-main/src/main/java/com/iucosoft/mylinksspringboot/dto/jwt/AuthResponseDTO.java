package com.iucosoft.mylinksspringboot.dto.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Setter
@Getter
@AllArgsConstructor
public class AuthResponseDTO {
    @NotNull
    private String token;

}
