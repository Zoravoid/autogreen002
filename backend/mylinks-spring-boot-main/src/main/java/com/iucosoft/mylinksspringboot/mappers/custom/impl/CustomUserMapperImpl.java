package com.iucosoft.mylinksspringboot.mappers.custom.impl;

import com.iucosoft.mylinksspringboot.dto.user.UserDTO;
import com.iucosoft.mylinksspringboot.dto.user.UserDTOCreate;
import com.iucosoft.mylinksspringboot.dto.user.UserDTONew;
import com.iucosoft.mylinksspringboot.dto.user.UserDTOUpdate;
import com.iucosoft.mylinksspringboot.entities.Role;
import com.iucosoft.mylinksspringboot.entities.User;
import com.iucosoft.mylinksspringboot.entities.UserStatus;
import com.iucosoft.mylinksspringboot.mappers.RoleMapper;
import com.iucosoft.mylinksspringboot.mappers.UserMapper;
import com.iucosoft.mylinksspringboot.mappers.UserStatusMapper;
import com.iucosoft.mylinksspringboot.mappers.custom.CustomUserMapper;
import com.iucosoft.mylinksspringboot.service.UserStatusService;
import com.iucosoft.mylinksspringboot.service.bridge.RoleUserService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomUserMapperImpl implements CustomUserMapper {

    private final UserStatusService userStatusService;
    private final UserStatusMapper userStatusMapper;
    UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    RoleMapper roleMapper = Mappers.getMapper(RoleMapper.class);

    private final RoleUserService roleUserService;

    @Autowired
    public CustomUserMapperImpl(RoleUserService roleUserService, UserStatusService userStatusService, UserStatusMapper userStatusMapper) {
        this.roleUserService = roleUserService;
        this.userStatusService = userStatusService;
        this.userStatusMapper = userStatusMapper;
    }

    @Override
    public UserDTO toDto(User user) {
        UserDTO userDTO = userMapper.toDto(user);
        List<Role> roleList = roleUserService.getRolesByUserId(user.getId());
        userDTO.setRoleList(roleMapper.mapEntityListToStringList(roleList));
        userDTO.setStatus(userStatusMapper.toDto(user.getUserStatus()));
        return userDTO;
    }

    @Override
    public User toEntity(UserDTO dto) {
        User user = userMapper.toEntity(dto);
        user.setUserStatus(userStatusService.findById(dto.getStatus().getId()));
        return user;
    }

    @Override
    public User userDtoCreateToUser(UserDTOCreate userDTOCreate) {
        return userMapper.userDtoCreateToUser(userDTOCreate);
    }

    @Override
    public UserDTOCreate userToUserDTOCreate(User user) {
        return userMapper.userToUserDTOCreate(user);
    }

    @Override
    public User userDTOUpdateToUser(UserDTOUpdate userDTOUpdate) {
        UserStatus userStatus = userStatusService.findById(userDTOUpdate.getUserStatusId());
        User user = userMapper.userDTOUpdateToUser(userDTOUpdate);
        user.setUserStatus(userStatus);
        return user;
    }

    @Override
    public UserDTONew userToUserDTONew(User user) {
        return userMapper.userToUserDTONew(user);
    }
}
