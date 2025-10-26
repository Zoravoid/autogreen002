package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.PermissionDTO;
import com.iucosoft.mylinksspringboot.entities.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionDTO toDto(Permission permission);

    Permission toEntity(PermissionDTO dto);

}
