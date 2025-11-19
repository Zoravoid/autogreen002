package com.iucosoft.mylinksspringboot.rest.controllers.impl;

import com.iucosoft.mylinksspringboot.dto.user.UserDTO;
import com.iucosoft.mylinksspringboot.dto.group.GroupDTO;
import com.iucosoft.mylinksspringboot.dto.group.GroupUpdateDTO;
import com.iucosoft.mylinksspringboot.entities.Group;
import com.iucosoft.mylinksspringboot.mappers.GroupMapper;
import com.iucosoft.mylinksspringboot.repositories.GroupRepository;
import com.iucosoft.mylinksspringboot.rest.RestIntegrationTest;
import com.iucosoft.mylinksspringboot.service.GroupService;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GroupApiTest extends RestIntegrationTest {
    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupMapper groupMapper;

    @Test
    void testGetAllGroupsApi_SuccessForAdminRole() throws Exception {
        MvcResult mvcResultCustomers = mockMvc.perform(getToURLWithAuthorizationToken("/api/groups", getAuthorizationTokenForAdminUser()))
                .andExpect(status().isOk())
                .andReturn();

        final String contentGetAllGroups = mvcResultCustomers.getResponse().getContentAsString();
        final JSONArray jsonPermissions = JsonPath.parse(contentGetAllGroups).read("$.content");
        Assertions.assertEquals(getGroups().size(), jsonPermissions.size());
    }
    @Test
    void testGetAllGroupsApi_SuccessForUserRole() throws Exception {
        MvcResult mvcResultCustomers = mockMvc.perform(getToURLWithAuthorizationToken("/api/groups", getAuthorizationTokenForDefaultUser()))
                .andExpect(status().isOk())
                .andReturn();

        final String contentGetAllGroups = mvcResultCustomers.getResponse().getContentAsString();
        final JSONArray jsonPermissions = JsonPath.parse(contentGetAllGroups).read("$.content");
        Assertions.assertEquals(getGroups().size(), jsonPermissions.size());
    }

    @Test
    void testCreateRoleApi_Success() throws Exception {
        int nrOfRoles = groupService.findAll().size();

        final GroupDTO groupDTO = getGroupDtoCreate();

        mockMvc.perform(postToURLWithAuthorizationTokenAsRequestBody("/api/groups", getAuthorizationTokenForAdminUser(), groupDTO))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(nrOfRoles + 1, groupService.findAll().size());
    }

    @Test
    void testUpdateGroupByIdApi_Success() throws Exception {
        final List<Group> groups = getGroups();

        Assertions.assertEquals("Admin Group", groups.get(0).getTitle());

        final GroupUpdateDTO updateDTO = groupMapper.toUpdateDto(groups.get(0));

        String updatedTitle = "Updated Title";
        updateDTO.setTitle(updatedTitle);


        MvcResult mvcResult = mockMvc.perform(putToURLWithAuthorizationTokenAsRequestBody("/api/groups/" + groups.get(0).getId(), getAuthorizationTokenForAdminUser(), updateDTO))
                .andExpect(status().isOk())
                .andReturn();

        final String contentGroupUpdate = mvcResult.getResponse().getContentAsString();
        final String title = JsonPath.parse(contentGroupUpdate).read("$.title");

        Assertions.assertEquals(updatedTitle, title);

    }

    @Test
    void testGetGroupByIdApi_Success() throws Exception {
        final List<Group> groups = getGroups();

        MvcResult mvcResult = mockMvc.perform(getToURLWithAuthorizationToken("/api/groups/" + groups.get(0).getId(), getAuthorizationTokenForAdminUser()))
                .andExpect(status().isOk())
                .andReturn();

        final String contentGetGroup = mvcResult.getResponse().getContentAsString();
        final String title = JsonPath.parse(contentGetGroup).read("$.title");

        Assertions.assertEquals(groups.get(0).getTitle(), title);
    }


    @Test
    void testDeleteGroupByIdApi() throws Exception {
        final List<Group> groups = getGroups();

        Assertions.assertEquals(2, groups.size());

        MvcResult mvcResult = mockMvc.perform(deleteToURLWithAuthorizationToken("/api/groups/" + groups.get(0).getId(), getAuthorizationTokenForAdminUser()))
                .andExpect(status().isOk())
                .andReturn();

        final String contentDeleteGroup = mvcResult.getResponse().getContentAsString();
        final String message = JsonPath.parse(contentDeleteGroup).read("$.message");
        Assertions.assertTrue(message.startsWith("Group with id"));

        final List<Group> groupsAfterDelete = getGroups();
        Assertions.assertEquals(1, groupsAfterDelete.size());
    }

    @Test
    void testGetGroupByIdApi_NOT_FOUND() throws Exception {
        final List<Group> groups = getGroups();

        MvcResult mvcResult = mockMvc.perform(getToURLWithAuthorizationToken("/api/groups/" + groups.size() + 1, getAuthorizationTokenForDefaultUser()))
                .andExpect(status().isNotFound())
                .andReturn();

        final String responseContent = mvcResult.getResponse().getContentAsString();
        final Integer statusCode = JsonPath.parse(responseContent).read("$.statusCode");
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), statusCode);
    }


    private List<Group> getGroups() {
        return groupRepository.findAll();
    }

    private GroupDTO getGroupDtoCreate() {
        UserDTO userDTO = UserDTO.builder()
                .username("user")
                .dateCreatedAccount(new Date())
                .password("passwprd")
                .email("new@gmail.com")
                .dateLastAccessed(new Date())
                .build();

        GroupDTO groupDTO = GroupDTO.builder()
                .title("New")
                .onlineUsers(200)
                .userDTO(userDTO)
                .build();

        return groupDTO;
    }
}
