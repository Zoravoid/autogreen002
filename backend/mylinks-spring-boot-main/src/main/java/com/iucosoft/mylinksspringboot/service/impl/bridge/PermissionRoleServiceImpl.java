package com.iucosoft.mylinksspringboot.service.impl.bridge;


import com.iucosoft.mylinksspringboot.dto.PermissionDTO;
import com.iucosoft.mylinksspringboot.entities.bridge.PermissionRole;
import com.iucosoft.mylinksspringboot.mappers.PermissionMapper;
import com.iucosoft.mylinksspringboot.repositories.bridge.PermissionRoleRepository;
import com.iucosoft.mylinksspringboot.service.bridge.PermissionRoleService;
import com.iucosoft.mylinksspringboot.service.impl.AbstractServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionRoleServiceImpl extends AbstractServiceImpl<PermissionRole, Long> implements PermissionRoleService {

    private final PermissionRoleRepository permissionRoleRepository;
    private PermissionMapper permissionMapper;

    public PermissionRoleServiceImpl(PermissionRoleRepository permissionRoleRepository, PermissionMapper permissionMapper) {
        this.permissionRoleRepository = permissionRoleRepository;
        this.permissionMapper = permissionMapper;
    }

    @Override
    protected JpaRepository<PermissionRole, Long> getRepository() {
        return permissionRoleRepository;
    }

    @Override
    public List<PermissionDTO> findAllPermissionsByRoleId(Long roleId) {

        return permissionRoleRepository.findAllPermissionsByRoleId(roleId)
                .stream().map(permissionMapper::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public void deleteByRoleId(Long roleId) {
        permissionRoleRepository.deleteByRoleId(roleId);
    }

    @Override
    public void deleteByPermissionId(Long permissionId) {
        permissionRoleRepository.deleteByPermissionId(permissionId);
    }
}
