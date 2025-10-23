package com.iucosoft.mylinksspringboot.rest.controllers.impl;

import com.iucosoft.mylinksspringboot.dto.jwt.AuthRequestDTO;
import com.iucosoft.mylinksspringboot.dto.personDetails.PersonDetailsDTOCreate;
import com.iucosoft.mylinksspringboot.dto.user.UserDTOCreate;
import com.iucosoft.mylinksspringboot.dto.user.UserDTONew;
import com.iucosoft.mylinksspringboot.entities.PersonDetails;
import com.iucosoft.mylinksspringboot.mappers.PersonDetailsMapper;
import com.iucosoft.mylinksspringboot.mappers.UserMapper;
import com.iucosoft.mylinksspringboot.rest.RestIntegrationTest;
import com.iucosoft.mylinksspringboot.service.CategoryService;
import com.iucosoft.mylinksspringboot.service.RoleService;
import com.iucosoft.mylinksspringboot.service.UserService;
import com.iucosoft.mylinksspringboot.service.authentication.SecurityTokenService;
import com.iucosoft.mylinksspringboot.service.bridge.RoleUserService;
import com.iucosoft.mylinksspringboot.util.AuthConstants;
import com.iucosoft.mylinksspringboot.util.jwt.JWTUtil;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthenticationApiTest extends RestIntegrationTest {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private SecurityTokenService securityTokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleUserService roleUserService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PersonDetailsMapper personDetailsMapper;

    @Test
    void createAuthenticationToken_SuccessForDefaultUser() throws Exception {
        AuthRequestDTO authRequest = new AuthRequestDTO("user1", "default");
        MvcResult mvcResult = mockMvc.perform(postToURLWithObjectAsRequestBody("/authenticate", authRequest))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        String token = JsonPath.parse(responseContent).read("$.token");
        Assertions.assertNotNull(token, "User token should not be null");

        String authorities = jwtUtil.extractAuthorities(token);
        Assertions.assertTrue(authorities.contains(AuthConstants.ROLE_USER), "Token authorities should contain ROLE_USER");
    }

    @Test
    void createAuthenticationToken_SuccessForAdminUser() throws Exception {
        AuthRequestDTO authRequest = new AuthRequestDTO("admin", "qwerty123");
        MvcResult mvcResult = mockMvc.perform(postToURLWithObjectAsRequestBody("/authenticate", authRequest))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        String token = JsonPath.parse(responseContent).read("$.token");
        Assertions.assertNotNull(token, "Admin token should not be null");

        String authorities = jwtUtil.extractAuthorities(token);
        Assertions.assertTrue(authorities.contains(AuthConstants.ROLE_ADMIN), "Token authorities should contain ROLE_ADMIN");
    }

    @Test
    void createAuthenticationToken_Failure() throws Exception {
        throw new Exception();
    }

//    @Test
//    void refreshToken_Success() throws Exception {
//        String invalidToken = "Bearer invalid.token.here";
//        mockMvc.perform(postToURLWithAuthorizationToken("/refresh", invalidToken))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    void refreshToken_Failure() throws Exception {
//        String invalidToken = "Bearer invalid.token.here";
//        mockMvc.perform(postToURLWithAuthorizationToken("/refresh", invalidToken))
//                .andExpect(status().isForbidden());
//    }

//    @Test
//    void createUserFailure_MissingRequiredField() throws Exception {
//        UserDTOCreate invalidUserDTO = createTestUserDTOCreate();
//        invalidUserDTO.setUsername(null);
//        mockMvc.perform(postToURLWithObjectAsRequestBody("/register", invalidUserDTO))
//                .andExpect(status().isBadRequest());
//    }

    @Test
    void createAuthenticationToken_InvalidCredentials() throws Exception {
        AuthRequestDTO authRequest = new AuthRequestDTO("user1", "wrongPassword");
        mockMvc.perform(postToURLWithObjectAsRequestBody("/authenticate", authRequest))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void createUser_Success() throws Exception {
        UserDTOCreate userDTOCreate = createTestUserDTOCreate();

        MvcResult result = mockMvc.perform(postToURLWithObjectAsRequestBody("/register", userDTOCreate))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        UserDTONew userDTONew = mapper.readValue(jsonResponse, UserDTONew.class);

        Assertions.assertNotNull(userDTONew);
        Assertions.assertEquals(userDTOCreate.getUsername(), userDTONew.getUsername());
        Assertions.assertNotNull(userDTONew.getPassword());
        Assertions.assertEquals(userDTONew.getUserStatusId(), 1L);
    }

    private UserDTOCreate createTestUserDTOCreate() {
        UserDTOCreate userDTOCreate = new UserDTOCreate();
        userDTOCreate.setUsername("newUserTest");
        userDTOCreate.setEmail("newUserTest@example.com");
        userDTOCreate.setPassword("default123");
        userDTOCreate.setRoles(Arrays.asList("USER"));
        return userDTOCreate;
    }
}