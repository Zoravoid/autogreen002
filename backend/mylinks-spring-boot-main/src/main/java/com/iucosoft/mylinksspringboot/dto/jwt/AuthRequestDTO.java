package com.iucosoft.mylinksspringboot.dto.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;

    private String language;

}
