package com.iucosoft.mylinksspringboot.rest.controllers.impl;

import com.iucosoft.mylinksspringboot.dto.PermissionDTO;
import com.iucosoft.mylinksspringboot.dto.role.RoleDTOCreate;
import com.iucosoft.mylinksspringboot.dto.role.RoleDTOUpdate;
import com.iucosoft.mylinksspringboot.entities.Permission;
import com.iucosoft.mylinksspringboot.entities.Role;
import com.iucosoft.mylinksspringboot.mappers.PermissionMapper;
import com.iucosoft.mylinksspringboot.mappers.RoleMapper;
import com.iucosoft.mylinksspringboot.rest.RestIntegrationTest;
import com.iucosoft.mylinksspringboot.service.PermissionService;
import com.iucosoft.mylinksspringboot.service.RoleService;
import com.iucosoft.mylinksspringboot.service.bridge.PermissionRoleService;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RoleApiTest extends RestIntegrationTest {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionRoleService permissionRoleService;


    @Test
    void testGetAllRoleApi_Success() throws Exception {
        MvcResult mvcResultCustomers = mockMvc.perform(getToURLWithAuthorizationToken("/api/roles", getAuthorizationTokenForAdminUser()))
                .andExpect(status().isOk())
                .andReturn();

        final String contentGetAllRoles = mvcResultCustomers.getResponse().getContentAsString();
        final JSONArray jsonPermissions = JsonPath.parse(contentGetAllRoles).read("$.content");
        Assertions.assertEquals(getRoles().size(), jsonPermissions.size());
    }

    @Test
    void testCreateRoleApi_Success() throws Exception {
        int nrOfRoles = roleService.findAll().size();

        final RoleDTOCreate roleDTOCreate = getRoleDtoCreate();

        mockMvc.perform(postToURLWithAuthorizationTokenAsRequestBody("/api/roles", getAuthorizationTokenForAdminUser(), roleDTOCreate))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(nrOfRoles + 1, roleService.findAll().size());
    }

    @Test
    void testUpdateRoleByIdApi_Success() throws Exception {
        final List<Role> roles = getRoles();
        final List<Permission> permissionByRoleId = getPermissionByRoleId(roles.get(0).getId());
        final List<Permission> permissions = getPermissions();

        Assertions.assertEquals("ADMIN", roles.get(0).getTitle());
        Assertions.assertEquals("Write", permissions.get(0).getTypeOfPermission());

        final RoleDTOUpdate roleDTOUpdate = roleMapper.toUpdateDto(roles.get(0));

        String updatedTitle = "Updated Title";
        roleDTOUpdate.setTitle(updatedTitle);
        roleDTOUpdate.setPermissionsDTOs(
                Arrays.asList(permissionMapper.toDto(permissions.get(1))));


        MvcResult mvcResult = mockMvc.perform(putToURLWithAuthorizationTokenAsRequestBody("/api/roles/" + roles.get(0).getId(), getAuthorizationTokenForAdminUser(), roleDTOUpdate))
                .andExpect(status().isOk())
                .andReturn();

        final String contentRoleUpdate = mvcResult.getResponse().getContentAsString();
        final String title = JsonPath.parse(contentRoleUpdate).read("$.title");
        final JSONArray jsonUpdatedPermissions = JsonPath.parse(contentRoleUpdate).read("$.permissionsDTOs");


        if (jsonUpdatedPermissions.size() > 0) {
            // Accessing the first item in the list
            Object firstItem = jsonUpdatedPermissions.get(0);

            // Extracting the value of the typeOfPermission field from the first item
            String typeOfPermission = JsonPath.parse(firstItem).read("$.typeOfPermission");

            Assertions.assertEquals(permissions.get(1).getTypeOfPermission(), typeOfPermission);
        }

        Assertions.assertEquals(updatedTitle, title);
        Assertions.assertEquals(roleDTOUpdate.getPermissionsDTOs().size(), jsonUpdatedPermissions.size());
    }

    @Test
    void testGetRoleByIdApi_Success() throws Exception {
        final List<Role> roles = getRoles();
        final List<Permission> permissionByRoleId = getPermissionByRoleId(roles.get(0).getId());

        MvcResult mvcResult = mockMvc.perform(getToURLWithAuthorizationToken("/api/roles/" + roles.get(0).getId(), getAuthorizationTokenForAdminUser()))
                .andExpect(status().isOk())
                .andReturn();

        final String contentGetRole = mvcResult.getResponse().getContentAsString();
        final String title = JsonPath.parse(contentGetRole).read("$.title");
        final JSONArray jsonPermissions = JsonPath.parse(contentGetRole).read("$.permissionsDTOs");

        Assertions.assertEquals(roles.get(0).getTitle(), title);
        Assertions.assertEquals(permissionByRoleId.size(), jsonPermissions.size());

    }

    @Test
    void testDeleteRoleByIdApi() throws Exception {
        final List<Role> roles = getRoles();

        Assertions.assertEquals(2, roles.size());

        MvcResult mvcResult = mockMvc.perform(deleteToURLWithAuthorizationToken("/api/roles/" + roles.get(0).getId(), getAuthorizationTokenForAdminUser()))
                .andExpect(status().isOk())
                .andReturn();

        final String contentAsString = mvcResult.getResponse().getContentAsString();
        final String message = JsonPath.parse(contentAsString).read("$.message");
        Assertions.assertEquals("Role has been deleted", message);

        final List<Role> rolesAfterDelete = getRoles();
        Assertions.assertEquals(1, rolesAfterDelete.size());
    }

    @Test
    void testGetRoleByIdApi_NOT_FOUND() throws Exception {
        final List<Role> roles = getRoles();

        MvcResult mvcResult = mockMvc.perform(getToURLWithAuthorizationToken("/api/roles/" + roles.size() + 1, getAuthorizationTokenForAdminUser()))
                .andExpect(status().isNotFound())
                .andReturn();

        final String responseContent = mvcResult.getResponse().getContentAsString();
        final Integer statusCode = JsonPath.parse(responseContent).read("$.statusCode");
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), statusCode);
    }

    private List<Role> getRoles() {
        return roleService.findAll();
    }

    private List<Permission> getPermissions() {
        return permissionService.findAll();
    }

    private List<Permission> getPermissionByRoleId(Long roleId) {
        List<PermissionDTO> permissionDTOS = permissionRoleService.findAllPermissionsByRoleId(roleId);
        return permissionDTOS.stream().map(permissionMapper::toEntity).collect(Collectors.toList());
    }

    private RoleDTOCreate getRoleDtoCreate() {
        Permission permission = permissionService.findAll().get(1);
        PermissionDTO permissionDTO = permissionMapper.toDto(permission);

        List<PermissionDTO> permissionDTOS = new ArrayList<>(Arrays.asList(permissionDTO));
        return new RoleDTOCreate("Title", permissionDTOS);
    }
}
