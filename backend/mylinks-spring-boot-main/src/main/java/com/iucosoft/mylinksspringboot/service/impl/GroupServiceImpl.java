package com.iucosoft.mylinksspringboot.service.impl;

import com.iucosoft.mylinksspringboot.dto.user.UserDTO;
import com.iucosoft.mylinksspringboot.dto.category.CategoryDTO;
import com.iucosoft.mylinksspringboot.dto.group.GroupDTO;
import com.iucosoft.mylinksspringboot.dto.link.LinkDTO;
import com.iucosoft.mylinksspringboot.entities.Category;
import com.iucosoft.mylinksspringboot.entities.Link;
import com.iucosoft.mylinksspringboot.entities.User;
import com.iucosoft.mylinksspringboot.entities.Group;
import com.iucosoft.mylinksspringboot.entities.bridge.GroupMember;
import com.iucosoft.mylinksspringboot.exceptions.BadRequestException;
import com.iucosoft.mylinksspringboot.exceptions.ResourceNotFoundException;
import com.iucosoft.mylinksspringboot.mappers.CategoryMapper;
import com.iucosoft.mylinksspringboot.mappers.GroupMapper;
import com.iucosoft.mylinksspringboot.mappers.LinkMapper;
import com.iucosoft.mylinksspringboot.mappers.UserMapper;
import com.iucosoft.mylinksspringboot.repositories.GroupRepository;
import com.iucosoft.mylinksspringboot.service.GroupService;
import com.iucosoft.mylinksspringboot.service.bridge.GroupCategoryService;
import com.iucosoft.mylinksspringboot.service.bridge.GroupLinkService;
import com.iucosoft.mylinksspringboot.service.bridge.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@Service
@Transactional
public class GroupServiceImpl extends AbstractServiceImpl<Group, Long> implements GroupService {

    private final GroupRepository groupRepository;

    private final GroupMemberService groupMemberService;
    private final GroupCategoryService groupCategoryService;
    private final GroupLinkService groupLinkService;

    private final CategoryMapper categoryMapper;
//    private final GroupMapper groupMapper;
    private final LinkMapper linkMapper;
//    private final UserMapper userMapper;


    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, GroupMemberService groupMemberService, GroupCategoryService groupCategoryService, GroupLinkService groupLinkService, LinkMapper linkMapper, CategoryMapper categoryMapper) {
        this.groupRepository = groupRepository;
        this.groupMemberService = groupMemberService;
        this.groupCategoryService = groupCategoryService;
        this.groupLinkService = groupLinkService;
//        this.groupMapper = groupMapper;
        this.linkMapper = linkMapper;
//        this.userMapper = userMapper;
        this.categoryMapper = categoryMapper;
    }


    @Override
    protected JpaRepository<Group, Long> getRepository() {
        return groupRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GroupDTO> getGroupsByUserOwnerId(Long userId, Pageable pageable) {
        Page<Group> groups = groupRepository.findGroupsByUserOwnerId(userId, pageable);
//        return groups.map(groupMapper::toDto);
        return new Page<GroupDTO>() {
            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public long getTotalElements() {
                return 0;
            }

            @Override
            public <U> Page<U> map(Function<? super GroupDTO, ? extends U> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public int getNumberOfElements() {
                return 0;
            }

            @Override
            public List<GroupDTO> getContent() {
                return Collections.EMPTY_LIST;
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator<GroupDTO> iterator() {
                return null;
            }
        };
    }

    @Override
    public GroupDTO addMemberToGroup(Long groupId, UserDTO userDTO) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new ResourceNotFoundException("User Group not found"));
//        User user = userMapper.toEntity(userDTO);
        User user = new User();
        GroupMember groupMember = GroupMember.builder()
                .group(group)
                .member(user)
                .build();

        groupMemberService.save(groupMember);
//        return groupMapper.toDto(group);
        return new GroupDTO();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CategoryDTO> getCategoriesByGroupId(Long groupId, Pageable pageable) {
        Page<Category> categories = groupCategoryService.getCategoriesByGroupId(groupId, pageable);
        return categories.map(categoryMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LinkDTO> getLinksByGroupId(Long groupId, Pageable pageable) {
        Page<Link> links = groupLinkService.getLinksByGroupId(groupId, pageable);
        return links.map(linkMapper::toDto);
    }

    @Override
    public User getOwnerByGroupId(Long groupId) {
        return groupRepository.findOwnerByGroupId(groupId);
    }

    @Override
    public Page<User> getMembersByGroupId(Long groupId, Pageable pageable) {
        return groupRepository.getMembersByGroupId(groupId, pageable);
    }

    @Override
    public void deleteLinkFromGroup(Long groupId, Long linkId) {
        groupLinkService.deleteLinkFromGroupByGroupIdAndLinkId(groupId, linkId);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDTO getCategoryFromGroup(Long categoryId, Long groupId) {
        Category category = groupCategoryService.getCategoryFromGroup(categoryId, groupId);
        return categoryMapper.toDto(category);
    }

    @Override
    public void deleteCategoryFromGroup(Long categoryId, Long groupId) {
        groupCategoryService.deleteCategoryFromGroup(categoryId, groupId);
    }

    @Override
    public Group findById(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("The group id must be not null");
        }

        return groupRepository.findAllById(Collections.singleton(id)).stream().findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Group could not be found!"));
    }

    @Override
    public void delete(Group group) {
        final Group groupToDelete =  groupRepository.findAllById(Collections.singleton(group.getId())).stream().findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Group could not be found!"));

        groupLinkService.deleteByGroupId(groupToDelete.getId());
        groupCategoryService.deleteByGroupId(groupToDelete.getId());
        groupMemberService.deleteByGroupId(groupToDelete.getId());
        groupRepository.delete(groupToDelete);
    }

}
