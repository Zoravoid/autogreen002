package com.iucosoft.mylinksspringboot.service;

import com.iucosoft.mylinksspringboot.dto.PermissionDTO;
import com.iucosoft.mylinksspringboot.entities.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PermissionService extends OperationIntf<Permission, Long>{

    PermissionDTO updatePermission(PermissionDTO permissionDTO, Long permissionId);
}
