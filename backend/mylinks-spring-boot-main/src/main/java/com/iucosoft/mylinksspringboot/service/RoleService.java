package com.iucosoft.mylinksspringboot.service;

import com.iucosoft.mylinksspringboot.dto.role.RoleDTOCreate;
import com.iucosoft.mylinksspringboot.dto.role.RoleDTOUpdate;
import com.iucosoft.mylinksspringboot.entities.Role;

import java.util.List;

public interface RoleService extends OperationIntf<Role, Long>{
    RoleDTOCreate saveRoleWithPermissions(RoleDTOCreate roleDTOCreate);

    RoleDTOUpdate updateRoleWithPermissionsByRoleId(Long roleId, RoleDTOUpdate roleDTOUpdate);

    List<Role> getRolesByTitles(List<String> titles);

    Role getRoleByTitle(String title);
}
