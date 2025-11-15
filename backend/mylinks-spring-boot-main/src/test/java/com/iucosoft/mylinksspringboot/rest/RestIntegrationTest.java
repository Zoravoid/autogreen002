package com.iucosoft.mylinksspringboot.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.iucosoft.mylinksspringboot.IntegrationTest;
import com.iucosoft.mylinksspringboot.dto.jwt.AuthRequestDTO;
import com.iucosoft.mylinksspringboot.entities.User;
import com.iucosoft.mylinksspringboot.util.AuthConstants;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(properties = {
        "spring.flyway.enabled=false",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class RestIntegrationTest extends IntegrationTest {

    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mockMvc;

    protected final ObjectMapper mapper = new ObjectMapper();

    @Value("default.user.password")
    String defaultUserPassword;


    @BeforeEach
    public void setup(){
        super.setup();
        mapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }



    protected MockHttpServletRequestBuilder getToURLWithAuthorizationToken(final String url, final String token){
        return get(url)
                .header(HttpHeaders.AUTHORIZATION, token)
                .contextPath("")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
    }

    protected  MockHttpServletRequestBuilder deleteToURLWithAuthorizationToken(final String url, final String token){
        return delete(url)
                .header(HttpHeaders.AUTHORIZATION, token)
                .contextPath("")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
    }

    protected MockHttpServletRequestBuilder postToURLWithAuthorizationTokenAsRequestBody(final String url, final String token, final Object object) {
        return post(url)
                .header(HttpHeaders.AUTHORIZATION, token)
                .contextPath("").content(objectToJsonString(object))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    protected MockHttpServletRequestBuilder putToURLWithAuthorizationTokenAsRequestBody(final String url, final String token, final Object object) {
        return put(url)
                .header(HttpHeaders.AUTHORIZATION, token)
                .contextPath("").content(objectToJsonString(object))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    protected MockHttpServletRequestBuilder postToURLWithObjectAsRequestBody(final String url, final Object object) {
        return post(url).contextPath("").content(objectToJsonString(object))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }


    protected String objectToJsonString(final Object obj) {
        try {
            if (obj instanceof String) {
                return ((String) obj);
            }
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected String getAuthorizationTokenForDefaultUser() throws Exception {
        final User user = getDefaultUser();
        final AuthRequestDTO authRequestDTO = new AuthRequestDTO(user.getUsername(), user.getPassword(), null);

        MvcResult mvcResultAuthenticate = mockMvc.perform(postToURLWithObjectAsRequestBody("/authenticate", authRequestDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        final String content = mvcResultAuthenticate.getResponse().getContentAsString();
        final String token = JsonPath.parse(content).read("$.token");
        Assertions.assertNotNull(token);
        return AuthConstants.BEARER + token;
    }


    protected String getAuthorizationTokenForAdminUser() throws Exception {
        final User user = getAdminUser();
        final AuthRequestDTO authRequestDTO = new AuthRequestDTO(user.getUsername(), user.getPassword(), null);

        MvcResult mvcResultAuthenticate = mockMvc.perform(postToURLWithObjectAsRequestBody("/authenticate", authRequestDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        final String content = mvcResultAuthenticate.getResponse().getContentAsString();
        final String token = JsonPath.parse(content).read("$.token");
        Assertions.assertNotNull(token);
        return AuthConstants.BEARER + token;
    }

}
