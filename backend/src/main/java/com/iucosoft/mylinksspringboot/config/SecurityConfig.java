package com.iucosoft.mylinksspringboot.config;

import com.iucosoft.mylinksspringboot.filters.HibernateFilterEnabler;
import com.iucosoft.mylinksspringboot.security.JWTFilter;
import com.iucosoft.mylinksspringboot.service.authentication.AuthenticationUserService;
import com.iucosoft.mylinksspringboot.util.AuthConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationUserService authenticationUserService;
    private final JWTFilter jwtFilter;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public SecurityConfig(final AuthenticationUserService authenticationUserService,
                          final JWTFilter jwtFilter) {
        this.authenticationUserService = authenticationUserService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean(name = "uniqueHibernateFilterEnabler")
    public HibernateFilterEnabler hibernateFilterEnabler() {
        return new HibernateFilterEnabler();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationUserService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/authenticate", "/register", "/refresh").permitAll()
                .and().authorizeRequests().antMatchers("/api/links/**", "/api/users/**", "/api/categories/**", "/api/groups/**", "/api/permissions/**", "/api/data/**").hasAnyAuthority(AuthConstants.ROLE_USER, AuthConstants.ROLE_ADMIN)
                .and().authorizeRequests().antMatchers("/api/roles/**").hasAuthority(AuthConstants.ROLE_ADMIN)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
