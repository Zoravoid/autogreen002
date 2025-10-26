package com.iucosoft.mylinksspringboot.config;

import com.iucosoft.mylinksspringboot.MyLinksSpringBootApplication;
import com.iucosoft.mylinksspringboot.filters.HibernateFilterEnabler;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//@EnableJpaAuditing(auditorAwareRef = "jpaAuditingHandlerTest")
//@TestConfiguration
//@EnableAutoConfiguration
//@ActiveProfiles("test")
//@TestPropertySource(properties = {"spring.config.location=classpath:application.properties"})


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest
@ComponentScan(basePackages = {"com.iucosoft"}) // path la project
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public @interface TestConfig {
}
