package com.iucosoft.mylinksspringboot.service.bridge;

import com.iucosoft.mylinksspringboot.dto.PermissionDTO;
import com.iucosoft.mylinksspringboot.entities.bridge.PermissionRole;
import com.iucosoft.mylinksspringboot.service.OperationIntf;
import java.util.List;

public interface PermissionRoleService extends OperationIntf<PermissionRole, Long> {

    List<PermissionDTO> findAllPermissionsByRoleId(Long roleId);
    void deleteByRoleId(Long roleId);
    void deleteByPermissionId(Long permissionId);
}
