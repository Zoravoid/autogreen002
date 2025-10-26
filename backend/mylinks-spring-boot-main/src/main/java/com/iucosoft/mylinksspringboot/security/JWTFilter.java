package com.iucosoft.mylinksspringboot.security;

import com.iucosoft.mylinksspringboot.context.UserContext;
import com.iucosoft.mylinksspringboot.util.AuthConstants;
import com.iucosoft.mylinksspringboot.util.jwt.JWTUtil;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final UserContext userContext;

    @Autowired
    public JWTFilter(JWTUtil jwtUtil, UserContext userContext) {
        this.jwtUtil = jwtUtil;
        this.userContext = userContext;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(AuthConstants.AUTHORIZATION);
        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith(AuthConstants.BEARER)) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            final String commaSeparatedListOfAuthorities = jwtUtil.extractAuthorities(jwt);
            final List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(commaSeparatedListOfAuthorities);
            final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            // Set the user's ID in UserContext
            Long userId = Long.valueOf(jwtUtil.extractUserId(jwt));
            userContext.setUserId(userId);

            //Set authorities in UserContext
            String userAuthorities = jwtUtil.extractAuthorities(jwt);
            userContext.setAuthorities(userAuthorities);
        }
        chain.doFilter(request, response);
    }
}
