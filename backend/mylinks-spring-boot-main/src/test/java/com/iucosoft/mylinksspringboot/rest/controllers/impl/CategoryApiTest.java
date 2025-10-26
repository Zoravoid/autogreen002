package com.iucosoft.mylinksspringboot.rest.controllers.impl;


import com.iucosoft.mylinksspringboot.dto.user.UserDTO;
import com.iucosoft.mylinksspringboot.dto.category.CategoryCreateDTO;
import com.iucosoft.mylinksspringboot.dto.category.CategoryUpdateDTO;
import com.iucosoft.mylinksspringboot.entities.Category;
import com.iucosoft.mylinksspringboot.mappers.CategoryMapper;
import com.iucosoft.mylinksspringboot.repositories.UserRepository;
import com.iucosoft.mylinksspringboot.rest.RestIntegrationTest;
import com.iucosoft.mylinksspringboot.service.CategoryService;
import com.iucosoft.mylinksspringboot.service.UserService;
import com.iucosoft.mylinksspringboot.util.RequestConstants;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@Transactional
public class CategoryApiTest extends RestIntegrationTest {

    @Autowired
    CategoryService categoryService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    CategoryMapper categoryMapper;

    @Test
    void testCreateCategoryApi_Success() throws Exception {
        int nrOfCategories = categoryService.findAll().size();
        log.debug("NrOfCategories: {}", nrOfCategories);
        final CategoryCreateDTO categoryCreateDTO = getCategoryCreateDto();
        log.debug("CategoryCreateDTO: {}", categoryCreateDTO);

        mockMvc.perform(postToURLWithAuthorizationTokenAsRequestBody("/api/categories", getAuthorizationTokenForAdminUser(), categoryCreateDTO))
                .andExpect(status().isOk())
                .andReturn();
        log.debug("categoryService.findAll().size(): {}", categoryService.findAll().size());
        Assertions.assertEquals(nrOfCategories + 1, categoryService.findAll().size());
    }

    @Test
    void testUpdateCategoryByIdApi_Success() throws Exception {
        final List<Category> categories = getCategories();

        Assertions.assertEquals("Admin Category", categories.get(0).getTitle());

        final CategoryUpdateDTO updateDTO = categoryMapper.toUpdateDto(categories.get(0));

        String updatedTitle = "Updated Title";
        updateDTO.setTitle(updatedTitle);

        MvcResult mvcResult = mockMvc.perform(putToURLWithAuthorizationTokenAsRequestBody(RequestConstants.GET_ALL_CATEGORIES + categories.get(0).getId(),getAuthorizationTokenForAdminUser(), updateDTO))
                .andExpect(status().isOk())
                .andReturn();

        final String responseContent = mvcResult.getResponse().getContentAsString();
        final String title = JsonPath.parse(responseContent).read("$.title");

        Assertions.assertEquals(updatedTitle, title);
    }

    @Test
    void testGetCategoryByIdApi_Success() throws Exception {
        final List<Category> categories = getCategories();

        MvcResult mvcResult = mockMvc.perform(getToURLWithAuthorizationToken(RequestConstants.GET_ALL_CATEGORIES + categories.get(0).getId(), getAuthorizationTokenForAdminUser()))
                .andExpect(status().isOk())
                .andReturn();

        final String responseContent = mvcResult.getResponse().getContentAsString();
        final String title = JsonPath.parse(responseContent).read("$.title");

        Assertions.assertEquals(categories.get(0).getTitle(), title);
    }


    @Test
    void testDeleteCategoryByIdApi() throws Exception {
        final List<Category> categories = getCategories();
         Assertions.assertEquals(2, categories.size());
         log.debug("Categories: " + categories);

        MvcResult mvcResult = mockMvc.perform(deleteToURLWithAuthorizationToken(RequestConstants.GET_ALL_CATEGORIES + categories.get(0).getId(), getAuthorizationTokenForAdminUser()))
                .andExpect(status().isOk())
                .andReturn();

        final String responseContent = mvcResult.getResponse().getContentAsString();
        final String message = JsonPath.parse(responseContent).read("$.message");
        Assertions.assertTrue( message.startsWith("Category with id"));

        final List<Category> categoriesAfterDelete = getCategories();
        Assertions.assertEquals(1, categoriesAfterDelete.size());
    }

    @Test
    void testGetCategoryByIdApi_NOT_FOUND() throws Exception {
        final List<Category> categories = getCategories();
        System.out.println("Categories size: " + categories.size());
        MvcResult mvcResult = mockMvc.perform(getToURLWithAuthorizationToken(RequestConstants.GET_ALL_CATEGORIES + (categories.size() + 1), getAuthorizationTokenForAdminUser()))
                .andExpect(status().isNotFound())
                .andReturn();

        final String responseContent = mvcResult.getResponse().getContentAsString();
        final Integer statusCode = JsonPath.parse(responseContent).read("$.statusCode");
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), statusCode);
    }

    @Test
    void testGetAllCategoriesApi_SuccessForAdminRole() throws Exception {
        MvcResult mvcResultCustomers = mockMvc.perform(getToURLWithAuthorizationToken(RequestConstants.GET_ALL_CATEGORIES, getAuthorizationTokenForAdminUser()))
                .andExpect(status().isOk())
                .andReturn();

        final String responseContent = mvcResultCustomers.getResponse().getContentAsString();
        final JSONArray jsonPermissions = JsonPath.parse(responseContent).read("$.content");
        Assertions.assertEquals(getCategories().size(), jsonPermissions.size());
    }

    @Test
    void testGetAllCategoriesApi_SuccessForUserRole() throws Exception {
        MvcResult mvcResultCustomers = mockMvc.perform(getToURLWithAuthorizationToken(RequestConstants.GET_ALL_CATEGORIES, getAuthorizationTokenForDefaultUser()))
                .andExpect(status().isOk())
                .andReturn();

        final String responseContent = mvcResultCustomers.getResponse().getContentAsString();
        final JSONArray jsonPermissions = JsonPath.parse(responseContent).read("$.content");
        Assertions.assertEquals(1, jsonPermissions.size());
    }

    private List<Category> getCategories() {
        return categoryService.findAll();
    }

    private CategoryCreateDTO getCategoryCreateDto() {
        UserDTO userDTO = UserDTO.builder()
                .username("user")
                .dateCreatedAccount(new Date())
                .password("password")
                .email("new@gmail.com")
                .dateLastAccessed(new Date())
                .build();

        CategoryCreateDTO createDTO = CategoryCreateDTO.builder()
                .title("New Category")
                .ownerId(userRepository.findAll().get(0).getId())
                .build();


        return createDTO;
    }

}
