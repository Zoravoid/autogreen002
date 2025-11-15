package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.UserStatusDTO;
import com.iucosoft.mylinksspringboot.entities.UserStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserStatusMapper {

    UserStatusDTO toDto(UserStatus userStatus);
    UserStatus toEntity(UserStatusDTO dto);
}
