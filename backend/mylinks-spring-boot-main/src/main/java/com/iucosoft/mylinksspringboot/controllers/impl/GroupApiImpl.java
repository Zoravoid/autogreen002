package com.iucosoft.mylinksspringboot.controllers.impl;

import com.iucosoft.mylinksspringboot.controllers.AbstractExceptionHandler;
import com.iucosoft.mylinksspringboot.controllers.GroupApi;
import com.iucosoft.mylinksspringboot.dto.MessageDTO;
import com.iucosoft.mylinksspringboot.dto.group.GroupCreateDTO;
import com.iucosoft.mylinksspringboot.dto.user.UserDTO;
import com.iucosoft.mylinksspringboot.dto.category.CategoryDTO;
import com.iucosoft.mylinksspringboot.dto.group.GroupDTO;
import com.iucosoft.mylinksspringboot.dto.group.GroupUpdateDTO;
import com.iucosoft.mylinksspringboot.dto.link.LinkDTO;
import com.iucosoft.mylinksspringboot.entities.Category;
import com.iucosoft.mylinksspringboot.entities.Group;
import com.iucosoft.mylinksspringboot.entities.User;
import com.iucosoft.mylinksspringboot.entities.bridge.GroupCategory;
import com.iucosoft.mylinksspringboot.entities.bridge.GroupMember;
import com.iucosoft.mylinksspringboot.mappers.GroupMapper;
import com.iucosoft.mylinksspringboot.mappers.UserMapper;
import com.iucosoft.mylinksspringboot.service.CategoryService;
import com.iucosoft.mylinksspringboot.service.GroupService;
import com.iucosoft.mylinksspringboot.service.LinkService;
import com.iucosoft.mylinksspringboot.service.UserService;
import com.iucosoft.mylinksspringboot.service.bridge.GroupCategoryService;
import com.iucosoft.mylinksspringboot.service.bridge.GroupMemberService;
import com.iucosoft.mylinksspringboot.util.AuthConstants;
import com.iucosoft.mylinksspringboot.util.jwt.JWTUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GroupApiImpl extends AbstractExceptionHandler implements GroupApi {
    private final GroupService groupService;
    private final UserService userService;
    private final LinkService linkService;
    private final GroupMemberService groupMemberService;
    private final GroupCategoryService groupCategoryService;
    private final CategoryService categoryService;
    private final GroupMapper groupMapper;
    private final UserMapper userMapper;
    private final JWTUtil jwtUtil;

    public GroupApiImpl(GroupService groupService, UserService userService, LinkService linkService, GroupMemberService groupMemberService, GroupCategoryService groupCategoryService, CategoryService categoryService, GroupMapper groupMapper, UserMapper userMapper, JWTUtil jwtUtil) {
        this.groupService = groupService;
        this.userService = userService;
        this.linkService = linkService;
        this.groupMemberService = groupMemberService;
        this.groupCategoryService = groupCategoryService;
        this.categoryService = categoryService;
        this.groupMapper = groupMapper;
        this.userMapper = userMapper;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public ResponseEntity<Page<GroupDTO>> getListOfAllGroups(Pageable pageable) {
        Page<Group> groups = groupService.findAllPaginated(pageable);
        Page<GroupDTO> groupDTOS = groups.map(groupMapper::toDto);
        return ResponseEntity.ok(groupDTOS);
    }

    @Override
    public ResponseEntity<GroupDTO> createGroup(GroupCreateDTO groupCreateDTO, String authorizationHeader) {

        //Get ownerId from JWT
        final String jwtToken = authorizationHeader.replace(AuthConstants.BEARER, "").trim();
        final Integer userId = jwtUtil.extractUserId(jwtToken);

        //TODO Add user no found exception
        final User owner = userService.findById(Long.valueOf(userId));

        Group group = Group.builder().owner(owner).title(groupCreateDTO.getTitle()).build();
        Group savedGroup = groupService.save(group);

        return ResponseEntity.ok(groupMapper.toDto(savedGroup));
    }

    @Override
    public ResponseEntity<GroupDTO> updateGroupById(Long groupId, GroupUpdateDTO groupUpdateDTO) {
        Group group = groupService.findById(groupId);
        group.setTitle(groupUpdateDTO.getTitle());
        Group updatedGroup = groupService.save(group);
        return ResponseEntity.ok(groupMapper.toDto(updatedGroup));
    }

    @Override
    public ResponseEntity<GroupDTO> getGroupById(Long groupId) {
        Group group = groupService.findById(groupId);
        return ResponseEntity.ok(groupMapper.toDto(group));
    }

    @Override
    public ResponseEntity<MessageDTO> deleteGroupById(Long groupId) {
//        Group group = groupService.findById(groupId);
        groupService.delete(Group.builder().id(groupId).build());
        return ResponseEntity.ok().body(new MessageDTO(String.format("Group with id = %s has been deleted", groupId), true));
    }

    @Override
    public ResponseEntity<Page<GroupDTO>> getGroupsByOwnerId(Long userId, Pageable pageable) {
        return ResponseEntity.ok(userService.getGroupsOwnedByUserId(userId, pageable));
    }

    @Override
    public ResponseEntity<Page<GroupDTO>> getGroupsByMemberId(Long userId, Pageable pageable) {
        Page<Group> groups = userService.getGroupsByUserMemberId(userId, pageable);
        return ResponseEntity.ok(groups.map(groupMapper::toDto));
    }

    @Override
    public ResponseEntity<GroupDTO> getGroupByLinkId(Long linkId) {
        final Group group = linkService.getGroupByLinkId(linkId);
        return ResponseEntity.ok().body(groupMapper.toDto(group));
    }

    @Override
    public ResponseEntity<Page<GroupDTO>> getGroupsByCategoryId(Long categoryId, Pageable pageable) {
        Page<GroupDTO> groups = categoryService.getGroupsByCategoryId(categoryId, pageable).map(group -> groupMapper.toDto(group));
        return ResponseEntity.ok().body(groups);
    }
}
