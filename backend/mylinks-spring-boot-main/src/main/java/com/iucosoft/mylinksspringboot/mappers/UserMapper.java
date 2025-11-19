package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.user.UserDTO;
import com.iucosoft.mylinksspringboot.dto.user.UserDTOCreate;
import com.iucosoft.mylinksspringboot.dto.user.UserDTONew;
import com.iucosoft.mylinksspringboot.dto.user.UserDTOUpdate;
import com.iucosoft.mylinksspringboot.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDto(User user);

    User toEntity(UserDTO dto);

    User userDtoCreateToUser(UserDTOCreate userDTOCreate);

    UserDTOCreate userToUserDTOCreate(User user);

    User userDTOUpdateToUser (UserDTOUpdate userDTOUpdate);

    @Mapping(source = "userStatus.id", target = "userStatusId")
    UserDTONew userToUserDTONew(User user);

    UserDTOUpdate userToUserDTOUpdate(User user);

}
