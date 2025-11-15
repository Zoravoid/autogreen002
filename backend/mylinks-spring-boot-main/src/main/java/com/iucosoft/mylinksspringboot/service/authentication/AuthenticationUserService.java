package com.iucosoft.mylinksspringboot.service.authentication;

import com.iucosoft.mylinksspringboot.entities.Role;
import com.iucosoft.mylinksspringboot.entities.User;
import com.iucosoft.mylinksspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthenticationUserService implements UserDetailsService {

    @Lazy
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final User user = userService.findByUsername(username);
        final List<Role> roles = userService.getRolesByUser(user);
        String rolesTitles =  roles.stream().map(role ->role.getTitle()).collect(Collectors.joining(", "));
        if (user == null) {
            throw new UsernameNotFoundException("Unknown user: " + username);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(rolesTitles).build();
    }

    //FIXME: ROLURILE EXTRASE DIN TABEL DE LEGATURA -> Era Asa:
// return org.springframework.security.core.userdetails.User.builder()
//         .username(user.getLogin())
//            .password(user.getPassword())
//            .roles(user.getUserRole().name()).build();

}