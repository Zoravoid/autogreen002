package com.iucosoft.mylinksspringboot.service.impl;

import com.iucosoft.mylinksspringboot.dto.MessageDTO;
import com.iucosoft.mylinksspringboot.dto.group.GroupDTO;
import com.iucosoft.mylinksspringboot.dto.UserStatusDTO;
import com.iucosoft.mylinksspringboot.entities.*;
import com.iucosoft.mylinksspringboot.exceptions.BadRequestException;
import com.iucosoft.mylinksspringboot.exceptions.ResourceNotFoundException;
import com.iucosoft.mylinksspringboot.exceptions.UserAlreadyExistException;
import com.iucosoft.mylinksspringboot.repositories.LinkRepository;
import com.iucosoft.mylinksspringboot.repositories.UserRepository;
import com.iucosoft.mylinksspringboot.service.CategoryService;
import com.iucosoft.mylinksspringboot.service.GroupService;
import com.iucosoft.mylinksspringboot.service.UserService;
import com.iucosoft.mylinksspringboot.service.UserStatusService;
import com.iucosoft.mylinksspringboot.service.bridge.GroupMemberService;
import com.iucosoft.mylinksspringboot.service.bridge.RoleUserService;
import com.iucosoft.mylinksspringboot.util.AuthConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.iucosoft.mylinksspringboot.context.UserContext.getAuthorities;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User, Long> implements UserService {

    private final UserRepository userRepository;
    private final CategoryService categoryService;
    private final UserStatusService userStatusService;
    private final GroupService groupService;
    private final GroupMemberService groupMemberService;
    private final RoleUserService roleUserService;
    private final PasswordEncoder passwordEncoder;
    private final LinkRepository linkRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           CategoryService categoryService,
                           UserStatusService userStatusService,
                           GroupService groupService,
                           GroupMemberService groupMemberService,
                           RoleUserService roleUserService,
                           PasswordEncoder passwordEncoder, LinkRepository linkRepository) {
        this.userRepository = userRepository;
        this.categoryService = categoryService;
        this.userStatusService = userStatusService;
        this.groupService = groupService;
        this.groupMemberService = groupMemberService;
        this.roleUserService = roleUserService;
        this.passwordEncoder = passwordEncoder;
        this.linkRepository = linkRepository;
    }

    @Override
    protected JpaRepository<User, Long> getRepository() {
        return userRepository;
    }

    @Override
    public User findById(Long aLong) {
        if (Objects.isNull(aLong)) {
            throw new BadRequestException("The user group id must be not null");
        }

        return userRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("User could not be found!"));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<Role> getRolesByUser(User user) {
        return roleUserService.getRolesByUserId(user.getId());
    }

    @Override
    public List<User> getUsersByRole(Role role) {
        return roleUserService.getUsersByRoleId(role.getId());
    }


    @Override
    @Transactional(readOnly = true)
    public PersonDetails getPersonDetailsByUserId(Long userId) {
        return userRepository.findPersonDetailsByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with id = %s not found",userId)));
    }

    @Override
    @Transactional(readOnly = true)
    public UserStatus getStatusByUserId(Long userId) {
        return userRepository.findUserStatusByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GroupDTO> getGroupsOwnedByUserId(Long userId, Pageable pageable) {
        return groupService.getGroupsByUserOwnerId(userId, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Group> getGroupsByUserMemberId(Long userId, Pageable pageable) {
        return groupMemberService.getGroupsByUserMemberId(userId, pageable);
    }

    @Override
    public Page<Category> getCategoriesByOwnerId(Long ownerId, Pageable pageable) {
        return categoryService.getCategoriesByOwnerId(ownerId, pageable);
    }

    @Override
    public User createUser(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new UserAlreadyExistException("User with this username or password already exist.");
        }

        final UserStatus registeredStatus = userStatusService.findById(1L);
        user.setUserStatus(registeredStatus);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        final User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public User updateUser(User user) {
        Optional<User> optionalUserToUpdate = userRepository.findById(user.getId());
        if (!optionalUserToUpdate.isPresent()) {
            throw new ResourceNotFoundException("User not found");
        }

        User userToUpdate = optionalUserToUpdate.get();
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.getPersonDetails().setFirstName(user.getPersonDetails().getFirstName());
        userToUpdate.getPersonDetails().setLastName(user.getPersonDetails().getLastName());
        userToUpdate.getPersonDetails().setPhoneNumber(user.getPersonDetails().getPhoneNumber());
        userToUpdate.getPersonDetails().setCountry(user.getPersonDetails().getCountry());

        //TODO De intrebat pe Corina (OS,IP,Device) - credca nu trebuie shimbate,doar cand userul se autentifica sale schimbam,
        // ar fi bine intrun tabel separat pentru Loging
        userToUpdate.getPersonDetails().setOperatingSystem(user.getPersonDetails().getOperatingSystem());
        userToUpdate.getPersonDetails().setIpAddress(user.getPersonDetails().getIpAddress());
        userToUpdate.getPersonDetails().setDevice(user.getPersonDetails().getDevice());

        if(getAuthorities().contains(AuthConstants.ROLE_ADMIN)) {
            userToUpdate.setUserStatus(user.getUserStatus());
        }

        User updatedUser = userRepository.save(userToUpdate);
        return updatedUser;
    }

    @Override
    public MessageDTO deleteUser(Long id) {
        findById(id);
        validateUserUsage(id);
        roleUserService.deleteRolesByUserId(id);
        userRepository.deleteById(id);

        String message = String.format("User with id = %s deleted successfully", id);
        return new MessageDTO(message, true);
    }

    @Override
    public boolean hasLinks(Long id) {
        return userRepository.hasLinks(id);
    }

    private void validateUserUsage(Long id) {
        if (hasLinks(id)) {
            throw new RuntimeException("Can't delete user because already has links");
        }
    }


}
