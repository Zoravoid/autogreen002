package com.iucosoft.mylinksspringboot.dto.user;

import com.iucosoft.mylinksspringboot.dto.personDetails.PersonDetailsDTOCreate;
import com.iucosoft.mylinksspringboot.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTOCreate {

    @NotNull(message = "error.user.username.notnull")
    @NotBlank(message = "error.user.username.notblank")
    private String username;

    @NotNull(message = "error.user.email.notnull")
    @NotBlank(message = "error.user.email.notblank")
    private String email;

    @NotNull(message = "error.user.password.notnull")
    @NotBlank(message = "error.user.password.notblank")
    private String password;

    @NotNull(message = "error.user.persondetails")
    private PersonDetailsDTOCreate personDetails;

//    @NotNull(message = "error.user.roles")
    private List<String> roles;
}
