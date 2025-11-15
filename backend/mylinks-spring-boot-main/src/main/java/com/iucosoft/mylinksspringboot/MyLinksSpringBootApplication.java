package com.iucosoft.mylinksspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableCaching
@EnableJpaAuditing
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.iucosoft"})

public class MyLinksSpringBootApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MyLinksSpringBootApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(MyLinksSpringBootApplication.class);
        springApplication.run(args);
    }

}
