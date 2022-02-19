package com.martikan.quizapi.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.martikan.quizapi.exception.ApiException;
import com.martikan.quizapi.payload.request.SignInDTO;
import com.martikan.quizapi.security.UserDetails;
import com.martikan.quizapi.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Authentication filter.
 */
@Slf4j
@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtils jwtUtils;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {

            if (!"application/x-www-form-urlencoded".equals(request.getHeader("Content-Type"))) {
                throw new ApiException("Content-Type must be 'application/x-www-form-urlencoded'");
            }

            final var credentials = new ObjectMapper()
                    .readValue(request.getInputStream(), SignInDTO.class);

            return getAuthenticationManager()
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    credentials.getEmail(),
                                    credentials.getPassword()
                            )
                    );

        } catch (IOException e) {
            throw new ApiException("Attempt authentication error", e);
        } catch (Exception e) {
            throw new ApiException(e.getMessage(), e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        final var userDetails = (UserDetails) authResult.getPrincipal();

        final var roles = Arrays.toString(userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toArray());

        response.addHeader("token", jwtUtils.generateToken(userDetails));
        response.addHeader("userId", userDetails.getId().toString());
        response.addHeader("roles", roles);
    }
}