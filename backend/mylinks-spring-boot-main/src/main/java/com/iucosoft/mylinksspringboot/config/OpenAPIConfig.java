package com.iucosoft.mylinksspringboot.config;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
//import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Value("${security.authorization-header:Authorization}")
    private String authorizationHeader;

    private static final String SECURITY_SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI apiDocs() {
        return new OpenAPI()
                .info(new Info()
                        .title("Backend REST API")
                        .description("REST API documentation with JWT authentication.")
                        .version("1.0")
                        .contact(new Contact().name("API Support"))
                        .license(new License().name("API License"))
                )
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .in(SecurityScheme.In.HEADER)
                                        .name(authorizationHeader)
                        )
                );
    }

    //@Bean
    //public GroupedOpenApi api() {
        //return GroupedOpenApi.builder()
                //.group("backend")
                //.packagesToScan("com.example") // change to your base package
                //.pathsToMatch("/**")
                //.build();
    //}
}