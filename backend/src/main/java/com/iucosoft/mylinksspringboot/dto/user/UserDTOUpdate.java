package com.iucosoft.mylinksspringboot.dto.user;

import com.iucosoft.mylinksspringboot.dto.personDetails.PersonDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOUpdate {

    private Long id;

    @NotNull(message = "error.user.username.notnull")
    @NotBlank(message = "error.user.username.notblank")
    @Size(max = 255, message = "error.user.username.size")
    private String username;

    @Email(message = "error.user.email")
    private String email;

    @NotNull(message = "error.user.password.notnull")
    @NotBlank(message = "error.user.password.notblank")
    private String password;

    @NotNull(message = "error.user.persondetails")
    private PersonDetailsDTO personDetails;

    private Long userStatusId;

    private List<String> roleList;

}
