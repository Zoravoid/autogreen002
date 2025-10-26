package com.iucosoft.mylinksspringboot.controllers.impl;


import com.iucosoft.mylinksspringboot.controllers.AbstractExceptionHandler;
import com.iucosoft.mylinksspringboot.controllers.RoleApi;
import com.iucosoft.mylinksspringboot.dto.MessageDTO;
import com.iucosoft.mylinksspringboot.dto.PermissionDTO;
import com.iucosoft.mylinksspringboot.dto.role.RoleDTO;
import com.iucosoft.mylinksspringboot.dto.role.RoleDTOCreate;
import com.iucosoft.mylinksspringboot.dto.role.RoleDTOList;
import com.iucosoft.mylinksspringboot.dto.role.RoleDTOUpdate;
import com.iucosoft.mylinksspringboot.entities.Role;
import com.iucosoft.mylinksspringboot.mappers.PermissionMapper;
import com.iucosoft.mylinksspringboot.mappers.RoleMapper;
import com.iucosoft.mylinksspringboot.service.PermissionService;
import com.iucosoft.mylinksspringboot.service.RoleService;
import com.iucosoft.mylinksspringboot.service.bridge.PermissionRoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleApiImpl extends AbstractExceptionHandler implements RoleApi {

    private RoleService roleService;
    private PermissionService permissionService;
    private PermissionRoleService permissionRoleService;
    private RoleMapper roleMapper;
    private PermissionMapper permissionMapper;

    public RoleApiImpl(
            RoleService roleService,
            PermissionService permissionService,
            PermissionRoleService permissionRoleService,
            RoleMapper roleMapper,
            PermissionMapper permissionMapper) {
        this.roleService = roleService;
        this.permissionService = permissionService;
        this.permissionRoleService = permissionRoleService;
        this.roleMapper = roleMapper;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public ResponseEntity<Page<RoleDTOList>> getListOfAllRoles(Pageable pageable) {
        Page<Role> roles = roleService.findAllPaginated(pageable);
        final Page<RoleDTOList> rolesDtos = roles.map(roleMapper::toDtoList);

        for (RoleDTOList roleDTO : rolesDtos) {
            List<PermissionDTO> permissions = permissionRoleService.findAllPermissionsByRoleId(roleDTO.getId());
            roleDTO.setPermissionsDTOs(permissions);
        }
        return ResponseEntity.ok(rolesDtos);
    }

    @Override
    public ResponseEntity<RoleDTOCreate> createRole(RoleDTOCreate roleDTOCreate) {
        return ResponseEntity.ok(roleService.saveRoleWithPermissions(roleDTOCreate));
    }

    @Override
    public ResponseEntity<RoleDTOUpdate> updateRoleById(Long roleId, RoleDTOUpdate roleDTOUpdate) {
        return ResponseEntity.ok(roleService.updateRoleWithPermissionsByRoleId(roleId, roleDTOUpdate));
    }

    @Override
    public ResponseEntity<RoleDTO> getRoleById(Long roleId) {

        Role role = roleService.findById(roleId);

        RoleDTO roleDTO = roleMapper.toDto(role);

        List<PermissionDTO> permissions = permissionRoleService.findAllPermissionsByRoleId(roleDTO.getId());
        roleDTO.setPermissionsDTOs(permissions);

        return ResponseEntity.ok(roleDTO);
    }

    @Override
    public ResponseEntity<MessageDTO> deleteRoleById(Long roleId) {

        roleService.deleteById(roleId);

        return ResponseEntity.ok(new MessageDTO("Role has been deleted", true));
    }

}
