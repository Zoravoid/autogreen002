package com.iucosoft.mylinksspringboot.dto.user;

import com.iucosoft.mylinksspringboot.dto.personDetails.PersonDetailsDTO;
import com.iucosoft.mylinksspringboot.entities.Category;
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
public class UserDTONew {

    private Long id;

    private String username;

    private String email;

    private String password;

    private Date dateLastAccessed;

    private Date dateCreatedAccount;

    private PersonDetailsDTO personDetails;

    private Long userStatusId;

    private List<String> roleList;

    private Category defaultCategory;
}
