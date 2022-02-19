package com.martikan.quizapi.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.martikan.quizapi.domain.role.Role;
import com.martikan.quizapi.exception.ApiException;
import com.martikan.quizapi.security.UserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * Utils class to take care of JWT tokens.
 */
@Slf4j
@Component
public class JwtUtils {

    @Value("${token.expiration}")
    private String expirationTime;

    @Value("${token.secret}")
    private String secret;

    public String generateToken(final UserDetails userDetails) {

        try {

            ObjectMapper mapper = new ObjectMapper();

            // Map the authorities properly.
            final var rolesJson = mapper.writeValueAsString(userDetails.getAuthorities());

            return Jwts.builder()
                    .claim("userEnabled", userDetails.isEnabled())
                    .claim("roles", rolesJson)
                    .claim("upa", userDetails.getPassword())
                    .setSubject(userDetails.getId().toString())
                    .setIssuer(userDetails.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(Objects.requireNonNull(expirationTime))))
                    .signWith(SignatureAlgorithm.HS512, secret)
                    .compact();

        } catch (JsonProcessingException e) {
            throw new ApiException(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    public UserDetails parseJwt(final String token) {

        ObjectMapper mapper = new ObjectMapper();

        try {

            final var claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            // Decode roles.
            final var rolesJson = claims.get("roles", String.class);

            final var rolesArray = mapper.readValue(rolesJson, Role[].class);

            final var roles = Arrays.asList(rolesArray);

            return UserDetails.builder()
                    .id(Long.parseLong(claims.getSubject()))
                    .username(claims.getIssuer())
                    .password(claims.get("upa", String.class))
                    .authorities(roles)
                    .enabled(claims.get("userEnabled", Boolean.class))
                    .build();

        } catch (JsonProcessingException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (Exception e) {
            throw new ApiException("JWT token validation error", e);
        }

    }


}