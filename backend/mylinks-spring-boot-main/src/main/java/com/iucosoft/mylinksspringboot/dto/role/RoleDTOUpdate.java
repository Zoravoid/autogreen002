package com.iucosoft.mylinksspringboot.dto.role;

import com.iucosoft.mylinksspringboot.dto.PermissionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTOUpdate {

    private Long id;

    @NotBlank
    private String title;

    private List<PermissionDTO> permissionsDTOs;
}
