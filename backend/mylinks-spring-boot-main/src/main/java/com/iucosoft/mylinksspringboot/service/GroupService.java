package com.iucosoft.mylinksspringboot.service;

import com.iucosoft.mylinksspringboot.dto.user.UserDTO;
import com.iucosoft.mylinksspringboot.dto.category.CategoryDTO;
import com.iucosoft.mylinksspringboot.dto.group.GroupDTO;
import com.iucosoft.mylinksspringboot.dto.link.LinkDTO;
import com.iucosoft.mylinksspringboot.entities.Group;
import com.iucosoft.mylinksspringboot.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupService extends OperationIntf<Group, Long> {

    Page<GroupDTO> getGroupsByUserOwnerId(Long userId, Pageable pageable);

    GroupDTO addMemberToGroup(Long groupId, UserDTO userDTO);

    Page<CategoryDTO> getCategoriesByGroupId(Long groupId, Pageable pageable);

    Page<LinkDTO> getLinksByGroupId(Long groupId, Pageable pageable);

    User getOwnerByGroupId(Long groupId);

    Page<User> getMembersByGroupId(final Long groupId,final Pageable pageable);

    void deleteLinkFromGroup(Long groupId, Long linkId);

    CategoryDTO getCategoryFromGroup(Long categoryId, Long groupId);

    void deleteCategoryFromGroup(Long categoryId, Long groupId);

}
