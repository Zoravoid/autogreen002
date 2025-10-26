package com.iucosoft.mylinksspringboot.service.impl;

import com.iucosoft.mylinksspringboot.dto.PermissionDTO;
import com.iucosoft.mylinksspringboot.entities.Permission;
import com.iucosoft.mylinksspringboot.exceptions.BadRequestException;
import com.iucosoft.mylinksspringboot.exceptions.ResourceNotFoundException;
import com.iucosoft.mylinksspringboot.mappers.PermissionMapper;
import com.iucosoft.mylinksspringboot.repositories.PermissionRepository;
import com.iucosoft.mylinksspringboot.service.PermissionService;
import com.iucosoft.mylinksspringboot.service.bridge.PermissionRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PermissionServiceImpl extends AbstractServiceImpl<Permission, Long> implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionRoleService permissionRoleService;
    private final PermissionMapper permissionMapper;

    @Autowired
    public PermissionServiceImpl(PermissionRepository permissionRepository, PermissionRoleService permissionRoleService, PermissionMapper permissionMapper) {
        this.permissionRepository = permissionRepository;
        this.permissionRoleService = permissionRoleService;
        this.permissionMapper = permissionMapper;
    }

    @Override
    protected JpaRepository<Permission, Long> getRepository() {
        return permissionRepository;
    }

    @Override
    public Permission findById(Long aLong) {
        if (Objects.isNull(aLong)) {
            throw new BadRequestException("The permission id must be not null");
        }

        return permissionRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Permission could not be found!"));
    }

    @Override
    public void deleteById(Long entityId) {
        if (Objects.isNull(entityId)) {
            throw new BadRequestException("The permission id must be not null");
        }

        Permission permission = permissionRepository.findById(entityId)
                .orElseThrow(() -> new ResourceNotFoundException("Permission could not be found!"));
        permissionRoleService.deleteByPermissionId(entityId);
        permissionRepository.delete(permission);
    }

    @Override
    public PermissionDTO updatePermission(PermissionDTO permissionDTO, Long permissionId) {
        if (Objects.isNull(permissionId)) {
            throw new BadRequestException("The permission id must be not null");
        }
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not update the permission."));

        permission.setTypeOfPermission(permissionDTO.getTypeOfPermission());
        permission.setResource(permissionDTO.getResource());
        Permission updatedPermission = permissionRepository.save(permission);

        return permissionMapper.toDto(updatedPermission);
    }
}
