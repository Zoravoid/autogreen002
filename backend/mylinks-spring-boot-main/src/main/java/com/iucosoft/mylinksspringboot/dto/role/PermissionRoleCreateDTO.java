package com.iucosoft.mylinksspringboot.dto.role;

import com.iucosoft.mylinksspringboot.dto.PermissionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRoleCreateDTO {
    
    private PermissionDTO permissionDTO;
    private RoleDTO roleDTO;
}
