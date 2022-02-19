package com.martikan.quizapi.controller;

import com.martikan.quizapi.domain.user.SimpleUser;
import com.martikan.quizapi.payload.request.SignUpDTO;
import com.martikan.quizapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Authentication controller.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;

    /**
     * Endpoint for Sign-up action.
     * ONLY students can/should register with it.
     * @param signUpRequest {@link SignUpDTO}
     * @return {@link SimpleUser}
     */
    @PostMapping("/signUp")
    public ResponseEntity<SimpleUser> signUp(@Valid @RequestBody final SignUpDTO signUpRequest) {
        return ResponseEntity.ok(userService.signUp(signUpRequest));
    }

}
