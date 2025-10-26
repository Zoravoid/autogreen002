package com.iucosoft.mylinksspringboot.controllers.impl;

import com.iucosoft.mylinksspringboot.controllers.AbstractExceptionHandler;
import com.iucosoft.mylinksspringboot.controllers.PermissionApi;
import com.iucosoft.mylinksspringboot.dto.MessageDTO;
import com.iucosoft.mylinksspringboot.dto.PermissionDTO;
import com.iucosoft.mylinksspringboot.entities.Permission;
import com.iucosoft.mylinksspringboot.mappers.PermissionMapper;
import com.iucosoft.mylinksspringboot.service.PermissionService;
import com.iucosoft.mylinksspringboot.service.bridge.PermissionRoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PermissionApiImpl extends AbstractExceptionHandler implements PermissionApi {

    private final PermissionService permissionService;
    private final PermissionRoleService permissionRoleService;
    private final PermissionMapper permissionMapper;

    public PermissionApiImpl(
            PermissionService permissionService,
            PermissionRoleService permissionRoleService,
            PermissionMapper permissionMapper) {
        this.permissionService = permissionService;
        this.permissionRoleService = permissionRoleService;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public ResponseEntity<Page<PermissionDTO>> getListOfAllPermissions(Pageable pageable) {

        final Page<Permission> permissions = permissionService.findAllPaginated(pageable);
        final Page<PermissionDTO> permissionsDto = permissions.map(permissionMapper::toDto);
        return ResponseEntity.ok().body(permissionsDto);
    }

    @Override
    public ResponseEntity<PermissionDTO> createPermission(PermissionDTO permissionDTO) {
        Permission permission = permissionMapper.toEntity(permissionDTO);
        Permission permissionSaved = permissionService.save(permission);
        return ResponseEntity.ok().body(permissionMapper.toDto(permissionSaved));
    }

    @Override
    public ResponseEntity<PermissionDTO> updatePermissionById(Long permissionId, PermissionDTO permissionDTO) {
        Permission permission = permissionService.findById(permissionId);
        permission.setResource(permissionDTO.getResource());
        permission.setTypeOfPermission(permissionDTO.getTypeOfPermission());

        Permission updatedPermission = permissionService.save(permission);

        return ResponseEntity.ok().body(permissionMapper.toDto(updatedPermission));

    }

    @Override
    public ResponseEntity<PermissionDTO> getPermissionById(Long permissionId) {
        Permission permission = permissionService.findById(permissionId);

        return ResponseEntity.ok(permissionMapper.toDto(permission));
    }

    @Override
    public ResponseEntity<MessageDTO> deletePermissionById(Long permissionId) {
        permissionService.deleteById(permissionId);
        return ResponseEntity.ok().body(new MessageDTO("Permission has been deleted", true));
    }


}
