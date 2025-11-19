package com.iucosoft.mylinksspringboot.rest.controllers.impl;

import com.iucosoft.mylinksspringboot.dto.PermissionDTO;
import com.iucosoft.mylinksspringboot.entities.Permission;
import com.iucosoft.mylinksspringboot.mappers.PermissionMapper;
import com.iucosoft.mylinksspringboot.rest.RestIntegrationTest;
import com.iucosoft.mylinksspringboot.service.PermissionService;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PermissionApiTest extends RestIntegrationTest {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionMapper permissionMapper;


    @Test
    void testGetAllPermissionApi_Success() throws Exception {
        MvcResult mvcResultCustomers = mockMvc.perform(getToURLWithAuthorizationToken("/api/permissions", getAuthorizationTokenForAdminUser()))
                .andExpect(status().isOk())
                .andReturn();

        final String contentPermissions = mvcResultCustomers.getResponse().getContentAsString();
        final JSONArray jsonPermissions = JsonPath.parse(contentPermissions).read("$.content");
        Assertions.assertEquals(getPermissions().size(), jsonPermissions.size());

    }

    @Test
    void testCreatePermissionApi_Success() throws Exception {
        Assertions.assertEquals(2, permissionService.findAll().size());

        final PermissionDTO permissionDTO = getPermissionDtoCreate();

        mockMvc.perform(postToURLWithAuthorizationTokenAsRequestBody("/api/permissions", getAuthorizationTokenForAdminUser(), permissionDTO))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(3, permissionService.findAll().size());
    }

    @Test
    void testUpdatePermissionByIdApi_Success() throws Exception {
        final List<Permission> permissions = getPermissions();

        Assertions.assertEquals("Write", permissions.get(0).getTypeOfPermission());

        final PermissionDTO permissionDTO = permissionMapper.toDto(permissions.get(0));
        permissionDTO.setTypeOfPermission("UpdatedType");


        MvcResult mvcResult = mockMvc.perform(putToURLWithAuthorizationTokenAsRequestBody("/api/permissions/" + permissions.get(0).getId(), getAuthorizationTokenForAdminUser(), permissionDTO))
                .andExpect(status().isOk())
                .andReturn();

        final String contentPermissionUpdate = mvcResult.getResponse().getContentAsString();
        final String typeOfPermission = JsonPath.parse(contentPermissionUpdate).read("$.typeOfPermission");

        Assertions.assertEquals("UpdatedType", typeOfPermission);
    }

    @Test
    void testGetPermissionByIdApi_Success() throws Exception {
        final List<Permission> permissions = getPermissions();

        MvcResult mvcResult = mockMvc.perform(getToURLWithAuthorizationToken("/api/permissions/" + permissions.get(0).getId(), getAuthorizationTokenForAdminUser()))
                .andExpect(status().isOk())
                .andReturn();

        final String contentGetPermission = mvcResult.getResponse().getContentAsString();
        final String typeOfPermission = JsonPath.parse(contentGetPermission).read("$.typeOfPermission");
        Assertions.assertEquals(permissions.get(0).getTypeOfPermission(), typeOfPermission);
    }

    @Test
    void testDeletePermissionByIdApi_Success() throws Exception {
        final List<Permission> permissions = getPermissions();

        Assertions.assertEquals(2, permissions.size());

        MvcResult mvcResult = mockMvc.perform(deleteToURLWithAuthorizationToken("/api/permissions/" + permissions.get(0).getId(), getAuthorizationTokenForAdminUser()))
                .andExpect(status().isOk())
                .andReturn();

        final String contentAsString = mvcResult.getResponse().getContentAsString();
        final String message = JsonPath.parse(contentAsString).read("$.message");
        Assertions.assertEquals("Permission has been deleted", message);

        final List<Permission> permissionsAfterDelete = getPermissions();
        Assertions.assertEquals(1, permissionsAfterDelete.size());
    }


    @Test
    void testGetPermissionByIdApi_NOT_FOUND() throws Exception {
        final List<Permission> permissions = getPermissions();

        MvcResult mvcResult = mockMvc.perform(getToURLWithAuthorizationToken("/api/permissions/" + permissions.size() + 1, getAuthorizationTokenForAdminUser()))
                .andExpect(status().isNotFound())
                .andReturn();

        final String contentGetPermission = mvcResult.getResponse().getContentAsString();
        final Integer statusCode = JsonPath.parse(contentGetPermission).read("$.statusCode");
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), statusCode);
    }


    private PermissionDTO getPermissionDtoCreate() {
        return new PermissionDTO("User group", "Write");
    }

    private List<Permission> getPermissions() {
        return permissionService.findAll();
    }


}
