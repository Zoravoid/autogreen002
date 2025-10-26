package com.iucosoft.mylinksspringboot.service;

import com.iucosoft.mylinksspringboot.dto.MessageDTO;
import com.iucosoft.mylinksspringboot.dto.category.CategoryDTO;
import com.iucosoft.mylinksspringboot.dto.group.GroupDTO;
import com.iucosoft.mylinksspringboot.dto.UserStatusDTO;
import com.iucosoft.mylinksspringboot.entities.*;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserService extends OperationIntf<User, Long>{

    User findByUsername(String username);

    List<Role> getRolesByUser(User user);
    List<User> getUsersByRole(Role role);

    PersonDetails getPersonDetailsByUserId(Long userId);

    UserStatus getStatusByUserId(Long userId);

    Page<GroupDTO> getGroupsOwnedByUserId(Long userId, Pageable pageable);

    Page<Group> getGroupsByUserMemberId(Long userId, Pageable pageable);

    Page<Category> getCategoriesByOwnerId(Long ownerId, Pageable pageable);

    User createUser(User user);

    User updateUser(User user);

    MessageDTO deleteUser(Long id);

    boolean hasLinks(Long id);

}
