package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.role.PermissionRoleCreateDTO;
import com.iucosoft.mylinksspringboot.dto.role.RoleDTO;
import com.iucosoft.mylinksspringboot.dto.role.RoleDTOList;
import com.iucosoft.mylinksspringboot.entities.Role;
import com.iucosoft.mylinksspringboot.entities.bridge.PermissionRole;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-30T18:57:18+0100",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 1.8.0_472 (Private Build)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDTO toDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDTO.RoleDTOBuilder roleDTO = RoleDTO.builder();

        roleDTO.id( role.getId() );
        roleDTO.title( role.getTitle() );

        return roleDTO.build();
    }

    @Override
    public Role toEntity(RoleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Role.RoleBuilder<?, ?> role = Role.builder();

        role.id( dto.getId() );
        role.title( dto.getTitle() );

        return role.build();
    }

    @Override
    public PermissionRole toEntityPermissionRole(PermissionRoleCreateDTO permissionRoleCreateDto) {
        if ( permissionRoleCreateDto == null ) {
            return null;
        }

        PermissionRole.PermissionRoleBuilder<?, ?> permissionRole = PermissionRole.builder();

        return permissionRole.build();
    }

    @Override
    public PermissionRoleCreateDTO toDtoPermissionRole(PermissionRole permissionRole) {
        if ( permissionRole == null ) {
            return null;
        }

        PermissionRoleCreateDTO.PermissionRoleCreateDTOBuilder permissionRoleCreateDTO = PermissionRoleCreateDTO.builder();

        return permissionRoleCreateDTO.build();
    }

    @Override
    public RoleDTOList toDtoList(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDTOList.RoleDTOListBuilder roleDTOList = RoleDTOList.builder();

        roleDTOList.id( role.getId() );
        roleDTOList.title( role.getTitle() );

        return roleDTOList.build();
    }
}
