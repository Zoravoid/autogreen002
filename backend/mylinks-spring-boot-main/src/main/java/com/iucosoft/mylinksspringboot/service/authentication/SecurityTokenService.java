package com.iucosoft.mylinksspringboot.service.authentication;

import com.iucosoft.mylinksspringboot.dto.jwt.AuthRequestDTO;
import com.iucosoft.mylinksspringboot.dto.jwt.AuthResponseDTO;
import com.iucosoft.mylinksspringboot.entities.User;
import com.iucosoft.mylinksspringboot.service.UserService;
import com.iucosoft.mylinksspringboot.util.AuthConstants;
import com.iucosoft.mylinksspringboot.util.jwt.JWTUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@Service
public class SecurityTokenService {

    private final AuthenticationManager authenticationManager;

    private final JWTUtil jwtTokenUtil;

    private final UserService userService;

    private final UserDetailsService userDetailsService;

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Autowired
    public SecurityTokenService(final AuthenticationManager authenticationManager,
                                final JWTUtil jwtTokenUtil,
                                final UserService userService, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    public ResponseEntity<AuthResponseDTO> createAuthenticationToken(final AuthRequestDTO authRequestDTO) {
        try {
            final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
            final User user = userService.findByUsername(authRequestDTO.getUsername());
            final String jwt = jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal(),
                                                                        user.getId(),
                                                                        authRequestDTO.getLanguage());
            return new ResponseEntity<>(new AuthResponseDTO(jwt), HttpStatus.OK);
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password is incorrect", e);
        }
    }

    public ResponseEntity<AuthResponseDTO> refreshAuthenticationToken(final HttpServletRequest request,
                                                                      String newLanguage) {
        final String authorizationHeader = request.getHeader(AuthConstants.AUTHORIZATION);
        String username = null;
        String language = "en";

        if (authorizationHeader != null && authorizationHeader.startsWith(AuthConstants.BEARER)) {
            String oldJwt = authorizationHeader.substring(7);
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(oldJwt)
                    .getBody();
            username = claims.getSubject();
            language = claims.get("lang", String.class);
//            username = jwtTokenUtil.extractUsername(oldJwt);
//            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(oldJwt).getBody();
        }

        if (newLanguage != null && !newLanguage.isEmpty()) {
            language = newLanguage;
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final User user = userService.findByUsername(username);
        final String newJwt = jwtTokenUtil.generateToken(userDetails, user.getId(), language);
        return new ResponseEntity<>(new AuthResponseDTO(newJwt), HttpStatus.OK);
    }

}