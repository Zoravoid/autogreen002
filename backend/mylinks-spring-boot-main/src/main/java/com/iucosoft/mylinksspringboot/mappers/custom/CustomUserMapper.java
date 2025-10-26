package com.iucosoft.mylinksspringboot.mappers.custom;

import com.iucosoft.mylinksspringboot.dto.user.UserDTO;
import com.iucosoft.mylinksspringboot.dto.user.UserDTOCreate;
import com.iucosoft.mylinksspringboot.dto.user.UserDTONew;
import com.iucosoft.mylinksspringboot.dto.user.UserDTOUpdate;
import com.iucosoft.mylinksspringboot.entities.User;

public interface CustomUserMapper {

    UserDTO toDto(User user);

    User toEntity(UserDTO dto);

    User userDtoCreateToUser(UserDTOCreate userDTOCreate);

    UserDTOCreate userToUserDTOCreate(User user);

    User userDTOUpdateToUser (UserDTOUpdate userDTOUpdate);

    UserDTONew userToUserDTONew(User user);
}
