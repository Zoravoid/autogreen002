package com.iucosoft.mylinksspringboot.service.authentication;

import com.iucosoft.mylinksspringboot.entities.Role;
import com.iucosoft.mylinksspringboot.entities.User;
import com.iucosoft.mylinksspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String userName = authentication.getName();
        final String password = authentication.getCredentials().toString();
        final User user = userService.findByUsername(userName);

        final List<Role> roles = userService.getRolesByUser(user);
//        List<String> rolesTitles = (List<String>) roles.stream().map(role -> role.getTitle());
        List<String> rolesTitles = roles.stream()
                .map(Role::getTitle)
                .map(String::trim)
                .collect(Collectors.toList());

        if (user == null) {
            throw new BadCredentialsException("Unknown user " + userName);
        }

        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Bad password");
        }


        UserDetails principal =  org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
//                .roles(String.valueOf(rolesTitles))
                .roles(rolesTitles.toArray(new String[0]))
                .build();
        return new UsernamePasswordAuthenticationToken(principal, password, principal.getAuthorities());


        //FIXME: ROles din tabel de  -> ERA ASA:
//        UserDetails principal =  org.springframework.security.core.userdetails.User.builder()
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .roles(user.getUserRole().name())
//                .build();
//        return new UsernamePasswordAuthenticationToken(principal, password, principal.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

