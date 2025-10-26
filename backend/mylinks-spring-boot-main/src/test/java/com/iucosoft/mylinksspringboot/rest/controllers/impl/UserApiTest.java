package com.iucosoft.mylinksspringboot.rest.controllers.impl;

import com.iucosoft.mylinksspringboot.dto.UserStatusDTO;
import com.iucosoft.mylinksspringboot.dto.personDetails.PersonDetailsDTO;
import com.iucosoft.mylinksspringboot.dto.personDetails.PersonDetailsDTOCreate;
import com.iucosoft.mylinksspringboot.dto.user.UserDTOCreate;
import com.iucosoft.mylinksspringboot.dto.user.UserDTOUpdate;
import com.iucosoft.mylinksspringboot.entities.Link;
import com.iucosoft.mylinksspringboot.entities.Role;
import com.iucosoft.mylinksspringboot.entities.User;
import com.iucosoft.mylinksspringboot.entities.UserStatus;
import com.iucosoft.mylinksspringboot.mappers.RoleMapper;
import com.iucosoft.mylinksspringboot.mappers.UserMapper;
import com.iucosoft.mylinksspringboot.rest.RestIntegrationTest;
import com.iucosoft.mylinksspringboot.service.RoleService;
import com.iucosoft.mylinksspringboot.service.UserService;
import com.iucosoft.mylinksspringboot.service.UserStatusService;
import com.iucosoft.mylinksspringboot.service.bridge.LinkOwnerService;
import com.iucosoft.mylinksspringboot.service.bridge.RoleUserService;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserApiTest extends RestIntegrationTest {

    @Autowired
    private UserService userService;
    @Autowired
    private LinkOwnerService linkOwnerService;
    @Autowired
    private UserStatusService userStatusService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleUserService roleUserService;

    @Test
    void TestGetUserByIdApi() throws Exception {
        final User user = getAllUsers().get(0);

        MvcResult mvcResult = mockMvc.perform(getToURLWithAuthorizationToken("/api/users/" + user.getId(), getAuthorizationTokenForAdminUser()))
                .andExpect(status().isOk())
                .andReturn();

        final String userDTO = mvcResult.getResponse().getContentAsString();
        final String username = JsonPath.parse(userDTO).read("$.username");
        final String id = JsonPath.parse(userDTO).read("$.id").toString();

        Assertions.assertEquals(username, user.getUsername());
        Assertions.assertEquals(id, user.getId().toString());
    }

    @Test
    void testListUsersApi() throws Exception {
        MvcResult mvcResult = mockMvc.perform(getToURLWithAuthorizationToken("/api/users", getAuthorizationTokenForAdminUser()))
                .andExpect(status().isOk())
                .andReturn();

        final String responseContent = mvcResult.getResponse().getContentAsString();
        final JSONArray userJsonArray = JsonPath.parse(responseContent).read("$.content");
        Assertions.assertEquals(userJsonArray.size(), getAllUsers().size());
    }

    @Test
    void testGetLinksByOwnerIdApi() throws Exception {
        List<User> users = getAllUsers();

        MvcResult mvcResultCustomers = mockMvc.perform(getToURLWithAuthorizationToken("/api/users/" + users.get(0).getId() + "/links", getAuthorizationTokenForAdminUser()))
                .andExpect(status().isOk())
                .andReturn();

        final String responseContent = mvcResultCustomers.getResponse().getContentAsString();
        final JSONArray jsonPermissions = JsonPath.parse(responseContent).read("$.content");
        Assertions.assertEquals(getLinksByOwnerId(users.get(0).getId()).size(), jsonPermissions.size());
    }

    @Test
    void testRegisterUserApi() throws Exception {
        Assertions.assertEquals(1, getAllUsers().size());
        final UserDTOCreate userDTOCreate = getUserDTOCreate();
        mockMvc.perform(postToURLWithObjectAsRequestBody("/register", userDTOCreate)).andExpect(status().isOk()).andReturn();
        Assertions.assertEquals(2, getAllUsers().size());
    }

    @Test
    void testUpdateUserApi() throws Exception {
        final List<User> users = getAllUsers();

        Assertions.assertEquals("admin", users.get(0).getUsername());

        final UserDTOUpdate userDTOUpdate = getUserDTOUpdate();
        userDTOUpdate.setId(users.get(0).getId());

        MvcResult mvcResult = mockMvc.perform(putToURLWithAuthorizationTokenAsRequestBody("/api/users/" + users.get(0).getId(), getAuthorizationTokenForAdminUser(), userDTOUpdate))
                .andExpect(status().isOk())
                .andReturn();

        final String contentUserDTOUpdate = mvcResult.getResponse().getContentAsString();
        final String username = JsonPath.parse(contentUserDTOUpdate).read("$.username");
        final String email = JsonPath.parse(contentUserDTOUpdate).read("$.email");

        Assertions.assertEquals("user", username);
        Assertions.assertEquals("new@email.com", email);
    }

    @Test
    void testDeleteUserApiIfNoRelationship() throws Exception {

        linkOwnerService.delete(linkOwnerService.findAll());

        final List<User> users = getAllUsers();
        Assertions.assertEquals(1, users.size());

        MvcResult mvcResult = mockMvc.perform(deleteToURLWithAuthorizationToken("/api/users/" + users.get(0).getId(), getAuthorizationTokenForAdminUser()))
                .andExpect(status().isOk())
                .andReturn();

        final String mvcResults = mvcResult.getResponse().getContentAsString();
        final String message = JsonPath.parse(mvcResults).read("$.message");

        Assertions.assertTrue(message.startsWith("User with id"));

        final List<User> editedUsers = getAllUsers();
        Assertions.assertEquals(0, editedUsers.size());
    }

    private UserDTOCreate getUserDTOCreate() {
        return new UserDTOCreate("testuser1", "test@example.com1", "test_password", new PersonDetailsDTOCreate("John", "Doe", "+1234567890", "USA", "Windows 10", "192.168.1.1", "Laptop"), Arrays.asList("ADMIN", "USER"));
    }

    private UserDTOUpdate getUserDTOUpdate() {
        UserStatus managedUserStatus = userStatusService.findByStatusName("ACTIVE");
        return new UserDTOUpdate(2L, "user", "new@email.com", "your_password", new PersonDetailsDTO(2L, "John", "Doe", "+1234567890", "USA", "Windows 10", "192.168.1.1", "Laptop"), managedUserStatus.getId(), Arrays.asList("USER"));
    }

    private List<User> getAllUsers() {
        return userService.findAll();
    }

    private List<Link> getLinksByOwnerId(Long ownerId) {
        Page<Link> links = linkOwnerService.getLinksByOwnerId(ownerId, Pageable.unpaged());
        return links.getContent();
    }

    @Test
    void testUpdateUserStatusAsAdmin_Success() throws Exception {
        User user = getAllUsers().get(2);
        UserStatus newStatus = userStatusService.findByStatusName("INACTIVE");

        UserDTOUpdate updateDTO = userMapper.userToUserDTOUpdate(user);
        updateDTO.setId(user.getId());
        updateDTO.setUserStatusId(newStatus.getId());
        List<Role>  roles = roleUserService.getRolesByUserId(user.getId());
        updateDTO.setRoleList(roleMapper.mapEntityListToStringList(roles));

        MvcResult mvcResult = mockMvc.perform(
                        putToURLWithAuthorizationTokenAsRequestBody(
                                "/api/users/" + user.getId(),
                                getAuthorizationTokenForAdminUser(),
                                updateDTO))
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        Long statusId = JsonPath.parse(response).read("$.status.id", Long.class);

        Assertions.assertEquals(newStatus.getId(), statusId);
    }

    @Test
    void testUpdateUserStatusAsDefaultUser_Failure() throws Exception {
        User user = getAllUsers().get(2);
        UserStatus newStatus = userStatusService.findByStatusName("INACTIVE");

        UserDTOUpdate updateDTO = userMapper.userToUserDTOUpdate(user);
        updateDTO.setId(user.getId());
        updateDTO.setUserStatusId(newStatus.getId());
        List<Role>  roles = roleUserService.getRolesByUserId(user.getId());
        updateDTO.setRoleList(roleMapper.mapEntityListToStringList(roles));

        MvcResult mvcResult = mockMvc.perform(
                        putToURLWithAuthorizationTokenAsRequestBody(
                                "/api/users/" + user.getId(),
                                getAuthorizationTokenForDefaultUser(),
                                updateDTO))
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        Long statusId = JsonPath.parse(response).read("$.status.id", Long.class);

        Assertions.assertNotEquals(newStatus.getId(), statusId);
    }

}
