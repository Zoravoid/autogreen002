package com.iucosoft.mylinksspringboot.controllers.impl;

import com.iucosoft.mylinksspringboot.controllers.AbstractExceptionHandler;
import com.iucosoft.mylinksspringboot.controllers.AuthenticationApi;
import com.iucosoft.mylinksspringboot.dto.jwt.AuthRequestDTO;
import com.iucosoft.mylinksspringboot.dto.jwt.AuthResponseDTO;
import com.iucosoft.mylinksspringboot.dto.user.UserDTO;
import com.iucosoft.mylinksspringboot.dto.user.UserDTOCreate;
import com.iucosoft.mylinksspringboot.dto.user.UserDTONew;
import com.iucosoft.mylinksspringboot.entities.Category;
import com.iucosoft.mylinksspringboot.entities.Role;
import com.iucosoft.mylinksspringboot.entities.User;
import com.iucosoft.mylinksspringboot.entities.bridge.RoleUser;
import com.iucosoft.mylinksspringboot.mappers.PersonDetailsMapper;
import com.iucosoft.mylinksspringboot.mappers.UserMapper;
import com.iucosoft.mylinksspringboot.service.CategoryService;
import com.iucosoft.mylinksspringboot.service.RoleService;
import com.iucosoft.mylinksspringboot.service.UserService;
import com.iucosoft.mylinksspringboot.service.authentication.SecurityTokenService;
import com.iucosoft.mylinksspringboot.service.bridge.RoleUserService;
import com.iucosoft.mylinksspringboot.util.AuthConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class AuthenticationApiImpl extends AbstractExceptionHandler implements AuthenticationApi {

    private final SecurityTokenService securityTokenService;

    private final UserService userService;

    private final RoleService roleService;

    private final RoleUserService roleUserService;

    private final CategoryService categoryService;

    private final UserMapper userMapper;

    private final PersonDetailsMapper personDetailsMapper;

    @Autowired
    public AuthenticationApiImpl(final SecurityTokenService securityTokenService,
                                 UserMapper userMapper, UserService userService, RoleService roleService, RoleUserService roleUserService, CategoryService categoryService, PersonDetailsMapper personDetailsMapper) {
        this.securityTokenService = securityTokenService;
        this.userMapper = userMapper;
        this.userService = userService;
        this.roleService = roleService;
        this.roleUserService = roleUserService;
        this.categoryService = categoryService;
        this.personDetailsMapper = personDetailsMapper;
    }

    @Override
    public ResponseEntity<AuthResponseDTO> createAuthenticationToken(final AuthRequestDTO authRequestDTO) {
        return securityTokenService.createAuthenticationToken(authRequestDTO);
    }

    @Override
    public ResponseEntity<AuthResponseDTO> refreshToken(final HttpServletRequest request,
                                                        @RequestParam(value = "lang", required = false) String newLanguage) {
        return securityTokenService.refreshAuthenticationToken(request, newLanguage);
    }

    @Override
    public ResponseEntity<UserDTONew> createUser(UserDTOCreate userDTOCreate) {
        final User userToSave = userMapper.userDtoCreateToUser(userDTOCreate);
        final Date currentDate = new Date();

        userToSave.setDateCreatedAccount(currentDate);
        userToSave.setDateLastAccessed(currentDate);

        final User savedUser = userService.createUser(userToSave);

        //TODO de adăugat procesarea rolurilor, momentan doar default role USER
//        Set Roles to User
        final List<String> roleNamesList = userDTOCreate.getRoles();
        List<Role> userRoles = roleService.getRolesByTitles(roleNamesList);
        List<String> savedRoles = new ArrayList<>();
        userRoles.forEach(role -> {
            savedRoles.add(roleUserService.save(new RoleUser(role, userToSave)).getRole().getTitle());
        });

        //Create default Category
        final Category defaultCategory = Category.builder().title(AuthConstants.DEFAULT).owner(savedUser).build();
        final Category savedCategory = categoryService.save(defaultCategory);

        final UserDTONew userDTONew = userMapper.userToUserDTONew(savedUser);
        userDTONew.setPersonDetails(personDetailsMapper.toDto(savedUser.getPersonDetails())); //TODO de modiicat momental maperul nu lecreaza "personal details" este null
        userDTONew.setRoleList(savedRoles);
        userDTONew.setDefaultCategory(savedCategory);
        return ResponseEntity.ok().body(userDTONew);
    }
}