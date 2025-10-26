package com.iucosoft.mylinksspringboot.service.impl;


import com.iucosoft.mylinksspringboot.dto.PermissionDTO;
import com.iucosoft.mylinksspringboot.dto.role.RoleDTOCreate;
import com.iucosoft.mylinksspringboot.dto.role.RoleDTOUpdate;
import com.iucosoft.mylinksspringboot.entities.Permission;
import com.iucosoft.mylinksspringboot.entities.Role;
import com.iucosoft.mylinksspringboot.entities.bridge.PermissionRole;
import com.iucosoft.mylinksspringboot.exceptions.BadRequestException;
import com.iucosoft.mylinksspringboot.exceptions.ResourceNotFoundException;
import com.iucosoft.mylinksspringboot.mappers.PermissionMapper;
import com.iucosoft.mylinksspringboot.mappers.RoleMapper;
import com.iucosoft.mylinksspringboot.repositories.RoleRepository;
import com.iucosoft.mylinksspringboot.service.PermissionService;
import com.iucosoft.mylinksspringboot.service.RoleService;
import com.iucosoft.mylinksspringboot.service.bridge.PermissionRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class RoleServiceImpl extends AbstractServiceImpl<Role, Long> implements RoleService {


    private RoleRepository roleRepository;
    private RoleMapper roleMapper;
    private PermissionMapper permissionMapper;
    private PermissionService permissionService;
    private PermissionRoleService permissionRoleService;

    @Autowired
    public RoleServiceImpl(PermissionService permissionService, RoleRepository roleRepository, RoleMapper roleMapper, PermissionMapper permissionMapper, PermissionRoleService permissionRoleService) {
        this.permissionService = permissionService;
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
        this.permissionMapper = permissionMapper;
        this.permissionRoleService = permissionRoleService;
    }

    @Override
    protected JpaRepository<Role, Long> getRepository() {
        return roleRepository;
    }


    @Override
    public Role findById(Long aLong) {

        if (Objects.isNull(aLong)) {
            throw new BadRequestException("The role id field must be not null");
        }

        return roleRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Role could not be found!"));
    }


    @Override
    public void deleteById(Long entityId) {

        if (Objects.isNull(entityId)) {
            throw new BadRequestException("The role id field must be not null");
        }

        Role role = roleRepository.findById(entityId)
                .orElseThrow(() -> new ResourceNotFoundException("Role could not be found!"));

        permissionRoleService.deleteByRoleId(role.getId());
        roleRepository.delete(role);
    }

    @Override
    public RoleDTOCreate saveRoleWithPermissions(RoleDTOCreate roleDTOCreate) {
        Role role = roleMapper.createDtoToEntity(roleDTOCreate);
        List<PermissionDTO> permissionDTOs = roleMapper.getPermissionListFromCreateDto(roleDTOCreate);

        Role savedRole = roleRepository.save(role);

        List<PermissionRole> savedPermissionRoles = savePermissionRoleRelationships(permissionDTOs, savedRole);

        // savedRole to createDTo
        RoleDTOCreate responseDTO = roleMapper.toCreateDto(savedRole);
        responseDTO.setPermissionsDTOs(savedPermissionRoles.stream()
                .map(permissionRole -> permissionMapper.toDto(permissionRole.getPermission()))
                .collect(Collectors.toList()));

        return responseDTO;
    }

    @Override
    public RoleDTOUpdate updateRoleWithPermissionsByRoleId(Long roleId, RoleDTOUpdate roleDTOUpdate) {

        if (Objects.isNull(roleId)) {
            throw new BadRequestException("The role id field must be not null");
        }

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not update the role."));

        //de adaugat if role = null -> ResourceNotFoundException
        role.setTitle(roleDTOUpdate.getTitle());
        Role updatedRole = roleRepository.save(role);

        //sterg legaturile vechi din tabelul de legatura, ca dupa sa le adaug pe cele noi
        permissionRoleService.deleteByRoleId(role.getId());

        //iau permisiunile noi din roleDTOUpdate
        List<PermissionDTO> permissionDTOs = roleMapper.getPermissionListFromUpdateDto(roleDTOUpdate);
        List<PermissionRole> savedPermissionRoles = savePermissionRoleRelationships(permissionDTOs, updatedRole);

        // savedRole to updateDTo
        RoleDTOUpdate responseDTO = roleMapper.toUpdateDto(updatedRole);
        responseDTO.setPermissionsDTOs(savedPermissionRoles.stream()
                .map(permissionRole -> permissionMapper.toDto(permissionRole.getPermission()))
                .collect(Collectors.toList()));

        return responseDTO;
    }

    @Override
    public List<Role> getRolesByTitles(List<String> titles) {
        List<Role> roles = new ArrayList<>();
        titles.forEach(title -> {
            roles.add(roleRepository.getRoleByTitle(title));
        });

        return roles;
    }

    @Override
    public Role getRoleByTitle(String title) {
        return roleRepository.getRoleByTitle(title);
    }

    private List<PermissionRole> savePermissionRoleRelationships(List<PermissionDTO> permissionDTOs, Role role) {
        // initializez o lista de entitati pentru tabelul de legatura
        // fiecare entitate PermissionRole este o legatura intre rol si o permisiune.
        List<PermissionRole> bridgePermissionRoles = new ArrayList<>();

        for (PermissionDTO permissionDTO : permissionDTOs) {
            Permission permission = permissionService.findById(permissionDTO.getId());
            PermissionRole permissionRole = new PermissionRole(permission, role);
            bridgePermissionRoles.add(permissionRole);
        }

        //salvez in bridge table DB toate entitatile de legatura
        List<PermissionRole> savedPermissionRoles = permissionRoleService.save(bridgePermissionRoles);

        return savedPermissionRoles;
    }

}
