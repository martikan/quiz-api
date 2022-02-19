package com.martikan.quizapi.service;

import com.martikan.quizapi.domain.role.RoleName;
import com.martikan.quizapi.domain.user.SimpleUser;
import com.martikan.quizapi.domain.user.User;
import com.martikan.quizapi.exception.BadRequestException;
import com.martikan.quizapi.payload.request.SignUpDTO;
import com.martikan.quizapi.payload.response.EmailAvailabilityDTO;
import com.martikan.quizapi.repository.UserRepository;
import com.martikan.quizapi.security.UserDetails;
import com.martikan.quizapi.util.ProjectionBuilderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Implementation for {@link UserService}.
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Load user for sign in process.
     * @param username Email address
     * @return {@link UserDetails}
     * @throws UsernameNotFoundException exception if user not found
     */
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        final var user = userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return new UserDetails(user);
    }

    /**
     * Check email availability.
     * @param email String
     * @return {@link EmailAvailabilityDTO}
     */
    @Override
    public EmailAvailabilityDTO checkEmailAvailability(final String email) {
        return EmailAvailabilityDTO.builder()
                .emailAvailable(!userRepository.existsUserByEmail(email))
                .build();
    }

    /**
     * Sign-up process. Only student can register in her/his own.
     * The teachers have to be added by admin or other teacher.
     * @param signUpRequest {@link SignUpDTO}
     * @return {@link SimpleUser}
     */
    @Override
    public SimpleUser signUp(final SignUpDTO signUpRequest) {

        if (userRepository.existsUserByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address is already exist.");
        }

        // Set up default role for student - `ROLE_USER`
        final var role = roleService.findRoleByName(RoleName.ROLE_USER);

        final var newUser = User.builder()
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .roles(Collections.singleton(role))
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .phone(signUpRequest.getPhoneNumber())
                .build();

        return ProjectionBuilderUtil.buildSimpleUser(userRepository.save(newUser));
    }
}
