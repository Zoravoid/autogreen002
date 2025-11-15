package com.iucosoft.mylinksspringboot.dto.user;

import com.iucosoft.mylinksspringboot.dto.UserStatusDTO;
import com.iucosoft.mylinksspringboot.dto.personDetails.PersonDetailsDTO;
import com.iucosoft.mylinksspringboot.dto.role.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "Username must be not null.")
    @Size(max = 255, message = "Username length must be smaller than 255 symbols.")
    private String username;

    @Email
    private String email;

    @NotBlank(message = "Password must be not null.")
    private String password;

    private Date dateLastAccessed;

    private Date dateCreatedAccount;

    private PersonDetailsDTO personDetails;

    private UserStatusDTO status;

    private List<String> roleList;
}
