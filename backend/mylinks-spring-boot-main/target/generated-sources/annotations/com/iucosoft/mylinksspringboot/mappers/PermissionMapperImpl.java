package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.PermissionDTO;
import com.iucosoft.mylinksspringboot.entities.Permission;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-04T15:51:04+0100",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 1.8.0_472 (Private Build)"
)
@Component
public class PermissionMapperImpl implements PermissionMapper {

    @Override
    public PermissionDTO toDto(Permission permission) {
        if ( permission == null ) {
            return null;
        }

        PermissionDTO.PermissionDTOBuilder permissionDTO = PermissionDTO.builder();

        permissionDTO.id( permission.getId() );
        permissionDTO.resource( permission.getResource() );
        permissionDTO.typeOfPermission( permission.getTypeOfPermission() );

        return permissionDTO.build();
    }

    @Override
    public Permission toEntity(PermissionDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Permission.PermissionBuilder<?, ?> permission = Permission.builder();

        permission.id( dto.getId() );
        permission.resource( dto.getResource() );
        permission.typeOfPermission( dto.getTypeOfPermission() );

        return permission.build();
    }
}
