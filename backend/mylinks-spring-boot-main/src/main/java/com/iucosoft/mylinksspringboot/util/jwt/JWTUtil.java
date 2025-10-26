package com.iucosoft.mylinksspringboot.util.jwt;

import com.iucosoft.mylinksspringboot.util.AuthConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public final class JWTUtil {

    @Value("${jwt.secret.key}")
    private String secretKey;
    @Value("${jwt.session.time}")
    private long sessionTime;

    public String generateToken(final UserDetails userDetails, final Long userId, final String language) {
        final Map<String, Object> claims = new HashMap<>();
        final String commaSeparatedListOfAuthorities = userDetails.getAuthorities().stream()
                .flatMap(authority -> Arrays.stream(authority.getAuthority().split(",")))
                .map(String::trim)
                .map(authority -> authority.startsWith(AuthConstants.ROLE_) ? authority : AuthConstants.ROLE_ + authority)
                .collect(Collectors.joining(","));
        claims.put("authorities", commaSeparatedListOfAuthorities);
        claims.put("userId", userId);
        claims.put("lang", language);
        return createToken(claims, userDetails.getUsername());
    }

    public String extractUsername(final String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractAuthorities(String token) {
        return extractClaim(token, claims -> (String) claims.get("authorities"));
    }

    public boolean isAdmin(String token) {
        String authorities = extractAuthorities(token);
        if (authorities == null || authorities.isEmpty()) {
            return false;
        }
        return Arrays.stream(authorities.split(","))
                .map(String::trim)
                .anyMatch(role -> role.equals(AuthConstants.ROLE_ADMIN));
    }

    public Integer extractUserId(String token) {
        return extractClaim(token, claims -> claims.get("userId", Integer.class));
    }

    private <T> T extractClaim(final String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(final String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    private String createToken(Map<String, Object> claims, final String subject) {
        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expireTimeFromNow())
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    private Date expireTimeFromNow() {
        return new Date(System.currentTimeMillis() + sessionTime);
    }
}
