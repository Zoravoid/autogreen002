package com.iucosoft.mylinksspringboot.rest.controllers.impl;


import com.iucosoft.mylinksspringboot.dto.link.LinkCreateDTO;
import com.iucosoft.mylinksspringboot.dto.link.LinkUpdateDTO;
import com.iucosoft.mylinksspringboot.entities.Link;
import com.iucosoft.mylinksspringboot.entities.User;
import com.iucosoft.mylinksspringboot.entities.Visibility;
import com.iucosoft.mylinksspringboot.mappers.LinkMapper;
import com.iucosoft.mylinksspringboot.mappers.UserMapper;
import com.iucosoft.mylinksspringboot.rest.RestIntegrationTest;
import com.iucosoft.mylinksspringboot.service.CategoryService;
import com.iucosoft.mylinksspringboot.service.LinkService;
import com.iucosoft.mylinksspringboot.service.UserService;
import com.iucosoft.mylinksspringboot.service.bridge.LinkOwnerService;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LinkApiTest extends RestIntegrationTest {

    private static final Logger log = LoggerFactory.getLogger(LinkApiTest.class);
    @Autowired
    private LinkService linkService;

    @Autowired
    private LinkOwnerService linkOwnerService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private LinkMapper linkMapper;

    @Autowired
    private UserMapper userMapper;

    public LinkApiTest() {
    }


    @Test
    void testGetAllLinkApi_SuccessForAdminRole() throws Exception {
        MvcResult mvcResultCustomers = mockMvc.perform(getToURLWithAuthorizationToken("/api/links", getAuthorizationTokenForAdminUser()))
                .andExpect(status().isOk())
                .andReturn();

        final String responseContent = mvcResultCustomers.getResponse().getContentAsString();
        final JSONArray jsonPermissions = JsonPath.parse(responseContent).read("$.content");
        Assertions.assertEquals(getLinks().size(), jsonPermissions.size());
    }

    @Test
    void testGetAllLinkApi_SuccessForUserRole() throws Exception {
        MvcResult mvcResultCustomers = mockMvc.perform(getToURLWithAuthorizationToken("/api/links", getAuthorizationTokenForDefaultUser()))
                .andExpect(status().isOk())
                .andReturn();

        final String responseContent = mvcResultCustomers.getResponse().getContentAsString();
        final JSONArray jsonPermissions = JsonPath.parse(responseContent).read("$.content");
        Assertions.assertEquals(0, jsonPermissions.size());
    }

    @Test
    void testCreateLinkApi_Success() throws Exception {
        int nrOfRoles = linkService.findAll().size();
        mockMvc.perform(postToURLWithAuthorizationTokenAsRequestBody("/api/links", getAuthorizationTokenForAdminUser(), getCreateDTO()))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(nrOfRoles + 1, linkService.findAll().size());
    }


    @Test
    void testUpdateByIdLinkApi_Success() throws Exception {
        final List<Link> links = getLinks();
        Assertions.assertEquals("First link", links.get(0).getTitle());

        MvcResult mvcResult = mockMvc.perform(putToURLWithAuthorizationTokenAsRequestBody("/api/links/" + links.get(0).getId(), getAuthorizationTokenForAdminUser(), getUpdateDTO()))
                .andExpect(status().isOk())
                .andReturn();

        final String responseContent = mvcResult.getResponse().getContentAsString();
        final String title = JsonPath.parse(responseContent).read("$.title");

        Assertions.assertEquals("Updated Title", title);
    }


    @Test
    void testGetLinkByIdApi_Success() throws Exception {
        final List<Link> links = getLinks();

        MvcResult mvcResult = mockMvc.perform(getToURLWithAuthorizationToken("/api/links/" + links.get(0).getId(), getAuthorizationTokenForAdminUser()))
                .andExpect(status().isOk())
                .andReturn();

        final String responseContent = mvcResult.getResponse().getContentAsString();
        final String title = JsonPath.parse(responseContent).read("$.title");
        Assertions.assertEquals(links.get(0).getTitle(), title);
    }

    @Test
    void testDeleteLinkByIdApi_Success() throws Exception {
        final List<Link> links = getLinks();

        Assertions.assertEquals(3, links.size());

        MvcResult mvcResult = mockMvc.perform(deleteToURLWithAuthorizationToken("/api/links/" + links.get(0).getId(), getAuthorizationTokenForAdminUser()))
                .andExpect(status().isOk())
                .andReturn();

        final String responseContent = mvcResult.getResponse().getContentAsString();
        final String message = JsonPath.parse(responseContent).read("$.message");
        Assertions.assertEquals(String.format("Link with id = %s deleted successfully", links.get(0).getId()), message);

        final List<Link> linksAfterDelete = getLinks();
        Assertions.assertEquals(2, linksAfterDelete.size());
    }

    @Test
    void testGetAllLinksByOwnerIdApi_Success() throws Exception {

        List<User> users = getUsers();

        MvcResult mvcResultCustomers = mockMvc.perform(getToURLWithAuthorizationToken("/api/users/" + users.get(0).getId() + "/links", getAuthorizationTokenForAdminUser()))
                .andExpect(status().isOk())
                .andReturn();

        final String responseContent = mvcResultCustomers.getResponse().getContentAsString();
        log.debug("responseContent = {}", responseContent);
        final JSONArray jsonPermissions = JsonPath.parse(responseContent).read("$.content");
        Assertions.assertEquals(getLinksByOwnerId(users.get(0).getId()).size(), jsonPermissions.size());
    }

    @Test
    void testGetLinkByIdApi_NOT_FOUND() throws Exception {
        final List<Link> links = getLinks();

        MvcResult mvcResult = mockMvc.perform(getToURLWithAuthorizationToken("/api/links/" + links.size() + 1, getAuthorizationTokenForAdminUser()))
                .andExpect(status().isNotFound())
                .andReturn();

        final String responseContent = mvcResult.getResponse().getContentAsString();
        final Integer statusCode = JsonPath.parse(responseContent).read("$.statusCode");
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), statusCode);
    }

    private List<Link> getLinks() {
        return linkService.findAll();
    }

    private LinkCreateDTO getCreateDTO() {
        User user = userService.findAll().get(0);

        LinkCreateDTO linkCreateDTO = LinkCreateDTO.builder()
                .dateLastAccessed(new Date())
                .url("link.com")
                .description("Description")
                .title("New link")
                .ownerId(user.getId())
                .visibility(Visibility.PUBLIC)
                .categoryIds(categoryService.getCategoriesIdsByOwnerId(user.getId()))
                .build();

        return linkCreateDTO;
    }

    private LinkUpdateDTO getUpdateDTO() {
        User user = userService.findAll().get(0);

        LinkUpdateDTO updateDTO = LinkUpdateDTO.builder()
                .dateLastAccessed(new Date())
                .url("www.newurl.com")
                .title("Updated Title")
                .description("Updated Description")
                .visibility(Visibility.PUBLIC)
                .categoryIds(categoryService.getCategoriesIdsByOwnerId(user.getId()))
                .build();

        return updateDTO;
    }

    private List<User> getUsers() {
        return userService.findAll();
    }

    private List<Link> getLinksByOwnerId(Long ownerId) {
        Page<Link> links = linkOwnerService.getLinksByOwnerId(ownerId, Pageable.unpaged());
        return links.getContent();
    }


}
