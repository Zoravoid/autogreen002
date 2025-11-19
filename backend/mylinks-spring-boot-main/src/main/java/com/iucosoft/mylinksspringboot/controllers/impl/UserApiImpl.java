package com.iucosoft.mylinksspringboot.controllers.impl;

import com.iucosoft.mylinksspringboot.controllers.AbstractExceptionHandler;
import com.iucosoft.mylinksspringboot.controllers.UserApi;

import com.iucosoft.mylinksspringboot.dto.MessageDTO;
import com.iucosoft.mylinksspringboot.dto.group.GroupDTO;
import com.iucosoft.mylinksspringboot.dto.UserStatusDTO;
import com.iucosoft.mylinksspringboot.dto.personDetails.PersonDetailsDTO;
import com.iucosoft.mylinksspringboot.dto.user.UserDTO;
import com.iucosoft.mylinksspringboot.dto.user.UserDTOCreate;
import com.iucosoft.mylinksspringboot.dto.user.UserDTONew;
import com.iucosoft.mylinksspringboot.dto.user.UserDTOUpdate;
import com.iucosoft.mylinksspringboot.entities.*;
import com.iucosoft.mylinksspringboot.entities.bridge.GroupMember;
import com.iucosoft.mylinksspringboot.entities.bridge.RoleUser;
import com.iucosoft.mylinksspringboot.mappers.*;
import com.iucosoft.mylinksspringboot.mappers.custom.CustomUserMapper;
import com.iucosoft.mylinksspringboot.service.*;
import com.iucosoft.mylinksspringboot.service.bridge.GroupMemberService;
import com.iucosoft.mylinksspringboot.service.bridge.RoleUserService;
import com.iucosoft.mylinksspringboot.util.AppConstants;
import com.iucosoft.mylinksspringboot.util.jwt.JWTUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserApiImpl extends AbstractExceptionHandler implements UserApi {

    private final JWTUtil jwtUtil;
    private final UserService userService;

    private final CustomUserMapper customUserMapper;

    private final CategoryService categoryService;

    private final RoleUserService roleUserService;

    private final GroupMemberService groupMemberService;

    private final LinkService linkService;
    private final RoleService roleService;
    private final GroupService groupService;

    private final PersonDetailsMapper personDetailsMapper;
    private final GroupMapper groupMapper;
    private final UserStatusMapper userStatusMapper;
    private final UserStatusService userStatusService;

    public UserApiImpl(JWTUtil jwtUtil, UserService userService,
                       CustomUserMapper customUserMapper,
                       CategoryService categoryService,
                       PersonDetailsMapper personDetailsMapper,
                       RoleUserService roleUserService, GroupMemberService groupMemberService,
                       LinkService linkService,
                       RoleService roleService,
                       GroupService groupService, GroupMapper groupMapper, UserStatusMapper userStatusMapper, UserStatusService userStatusService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.customUserMapper = customUserMapper;
        this.categoryService = categoryService;
        this.personDetailsMapper = personDetailsMapper;
        this.roleUserService = roleUserService;
        this.groupMemberService = groupMemberService;
        this.linkService = linkService;
        this.roleService = roleService;
        this.groupService = groupService;
        this.groupMapper = groupMapper;
        this.userStatusMapper = userStatusMapper;
        this.userStatusService = userStatusService;
    }

    @Override
    public ResponseEntity<UserDTO> getUserById(Long userId) {
        User user = userService.findById(userId);
        return ResponseEntity.ok(customUserMapper.toDto(user));
    }

    @Override
    public ResponseEntity<Page<UserDTO>> getPageUser(Pageable pageable) {
        Page<User> pageUser = userService.findAllPaginated(pageable);
        return ResponseEntity.ok(pageUser.map(customUserMapper::toDto));
    }

    @Override
    public ResponseEntity<UserDTONew> createUser(UserDTOCreate userDTOCreate) {
        final User userToSave = customUserMapper.userDtoCreateToUser(userDTOCreate);
        final Date currentDate = new Date();


        userToSave.setDateCreatedAccount(currentDate);
        userToSave.setDateLastAccessed(currentDate);

        final User savedUser = userService.createUser(userToSave);

        //Set Roles to User
        final List<String> roleNamesList = userDTOCreate.getRoles();
        List<Role> userRoles = roleService.getRolesByTitles(roleNamesList);
        List<String> savedRoles = new ArrayList<>();
        userRoles.forEach(role -> {
            savedRoles.add(roleUserService.save(new RoleUser(role, userToSave)).getRole().getTitle());
        });

        //Create default Category
        final Category defaultCategory = Category.builder().title("default").owner(savedUser).build();
        final Category savedCategory = categoryService.save(defaultCategory);

        final UserDTONew userDTONew = customUserMapper.userToUserDTONew(savedUser);
        userDTONew.setPersonDetails(personDetailsMapper.toDto(savedUser.getPersonDetails()));
        userDTONew.setRoleList(savedRoles);
        userDTONew.setDefaultCategory(savedCategory);
        return ResponseEntity.ok().body(userDTONew);
    }

    @Override
    public ResponseEntity<UserDTO> updateUser(Long userId, UserDTOUpdate userDTOUpdate) {
        User userToUpdate = customUserMapper.userDTOUpdateToUser(userDTOUpdate);

        User updatedUser = userService.updateUser(userToUpdate);

        roleUserService.deleteRolesByUserId(userDTOUpdate.getId());
        final List<String> roleNamesList = userDTOUpdate.getRoleList();
        List<Role> userRoles = roleService.getRolesByTitles(roleNamesList);
        List<String> savedRoles = new ArrayList<>();
        userRoles.forEach(role -> {
            RoleUser roleUser = new RoleUser(role, updatedUser);
            RoleUser savedRoleUser = roleUserService.save(roleUser);
            savedRoles.add(savedRoleUser.getRole().getTitle());
        });

        UserDTO updatedUserDTO = customUserMapper.toDto(updatedUser);
//        updatedUserDTO.setStatus(userStatusMapper.toDto(updatedUser.getUserStatus()));
        updatedUserDTO.setRoleList(savedRoles);
        return ResponseEntity.ok().body(updatedUserDTO);
    }

    @Override
    public ResponseEntity<MessageDTO> deleteUserById(Long userId) {
        final MessageDTO message = userService.deleteUser(userId);
        return ResponseEntity.ok().body(message);
    }

    @Override
    public ResponseEntity<PersonDetailsDTO> getPersonDetailsByUserId(Long userId) {
        PersonDetails personDetails = userService.getPersonDetailsByUserId(userId);
        return ResponseEntity.ok(personDetailsMapper.toDto(personDetails));
    }

    @Override
    public ResponseEntity<UserStatusDTO> getUserStatusByUserId(Long userId) {
        UserStatus userStatus = userService.getStatusByUserId(userId);
        return ResponseEntity.ok(userStatusMapper.toDto(userStatus));
    }

    @Override
    public ResponseEntity<UserDTO> getOwnerByCategoryId(Long categoryId) {
        final User owner = categoryService.getOwnerByCategoryId(categoryId);
        return ResponseEntity.ok().body(customUserMapper.toDto(owner));
    }

    @Override
    public ResponseEntity<UserDTO> getOwnerByLinkId(Long linkId) {
        final User owner = linkService.getOwnerByLinkId(linkId);
        return ResponseEntity.ok().body(customUserMapper.toDto(owner));
    }

    @Override
    public ResponseEntity<UserDTO> getOwnerByGroupId(Long groupId) {
        return ResponseEntity.ok().body(customUserMapper.toDto(groupService.getOwnerByGroupId(groupId)));
    }

    @Override
    public ResponseEntity<Page<UserDTO>> getMembersByGroupId(Long groupId, final Pageable pageable) {
        final Page<UserDTO> members = groupService.getMembersByGroupId(groupId, pageable).map(member -> customUserMapper.toDto(member));
        return ResponseEntity.ok().body(members);
    }

    @Override
    public ResponseEntity<GroupDTO> addMemberToGroup(Long groupId, UserDTO userDTO) throws Exception {
        if (!groupMemberService.existsByMemberIdAndAndGroupId(userDTO.getId(), groupId)) {
            GroupDTO groupDTO = groupService.addMemberToGroup(groupId, userDTO);
            return ResponseEntity.ok(groupDTO);
        } else {
            throw new Exception(AppConstants.Messages.MEMBER_ALREADY_IN_GROUP);
        }
    }

    @Override
    public ResponseEntity<GroupDTO> editMembersInGroup(Long groupId, List<Long> memberIds) {
        groupMemberService.deleteByGroupId(groupId);
        final Group group = groupService.findById(groupId);
        final List<GroupMember> groupMembers = memberIds.stream().map(id -> GroupMember.builder().group(group).member(userService.findById(id)).build()).collect(Collectors.toList());
        groupMemberService.save(groupMembers);
        final Group updatedGroup = groupService.findById(groupId);
        return ResponseEntity.ok().body(groupMapper.toDto(updatedGroup));
    }

    @Override
    public ResponseEntity<MessageDTO> removeMemberFromGroup(Long groupId, UserDTO userDTO) {
        groupMemberService.deleteByGroupIdAndMemberId(groupId, userDTO.getId());
        return ResponseEntity.ok().body(new MessageDTO(AppConstants.Messages.MEMBER_REMOVED_FROM_GROUP_SUCCESSFULLY, true));
    }

    //TODO v2 findUserByUsername


}
