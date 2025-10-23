package com.iucosoft.mylinksspringboot.mappers;


import com.iucosoft.mylinksspringboot.dto.PermissionDTO;
import com.iucosoft.mylinksspringboot.dto.role.*;
import com.iucosoft.mylinksspringboot.entities.Role;
import com.iucosoft.mylinksspringboot.entities.User;
import com.iucosoft.mylinksspringboot.entities.bridge.PermissionRole;
import com.iucosoft.mylinksspringboot.service.UserService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDTO toDto(Role role);
    Role toEntity(RoleDTO dto);
    PermissionRole toEntityPermissionRole(PermissionRoleCreateDTO permissionRoleCreateDto);
    PermissionRoleCreateDTO toDtoPermissionRole(PermissionRole permissionRole);

    RoleDTOList toDtoList(Role role);
    default RoleDTOCreate toCreateDto(Role role){
        RoleDTOCreate roleDTOCreate = new RoleDTOCreate();
        roleDTOCreate.setTitle(role.getTitle());
        return roleDTOCreate;
    }

    default RoleDTOUpdate toUpdateDto(Role role){
        RoleDTOUpdate roleDTOUpdate = new RoleDTOUpdate();
        roleDTOUpdate.setTitle(role.getTitle());
        roleDTOUpdate.setId(role.getId());
        return roleDTOUpdate;
    }
    default Role createDtoToEntity(RoleDTOCreate createDto){
        Role role = new Role();
        role.setTitle(createDto.getTitle());
        return role;
    }

    default List<PermissionDTO> getPermissionListFromCreateDto(RoleDTOCreate createDto){
        if(Objects.isNull(createDto.getPermissionsDTOs())){
            return new ArrayList<PermissionDTO>();
        }
        return createDto.getPermissionsDTOs();
    }


    default Role updateDtoToEntity(RoleDTOUpdate updateDto){
        Role role = new Role();
        role.setTitle(updateDto.getTitle());
        return role;
    }

    default List<PermissionDTO> getPermissionListFromUpdateDto(RoleDTOUpdate updateDto){

        if(Objects.isNull(updateDto.getPermissionsDTOs())){
            return new ArrayList<PermissionDTO>();
        }

        return updateDto.getPermissionsDTOs();
    }

    default List<String> mapEntityListToStringList(List<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        return roles.stream()
                .map(Role::getTitle)
                .collect(Collectors.toList());
    }

}
