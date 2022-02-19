package com.martikan.quizapi.service;

import com.martikan.quizapi.domain.user.SimpleUser;
import com.martikan.quizapi.payload.request.SignUpDTO;
import com.martikan.quizapi.payload.response.EmailAvailabilityDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Service for {@link com.martikan.quizapi.domain.user.User} and authentication's stuffs.
 */
public interface UserService extends UserDetailsService {

    EmailAvailabilityDTO checkEmailAvailability(final String email);

    SimpleUser signUp(final SignUpDTO signUpRequest);

}
