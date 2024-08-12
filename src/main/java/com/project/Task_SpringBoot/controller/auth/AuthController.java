package com.project.Task_SpringBoot.controller.auth;

import com.project.Task_SpringBoot.dto.SignUpRequest;
import com.project.Task_SpringBoot.dto.UserDto;
import com.project.Task_SpringBoot.services.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody SignUpRequest signUpRequest) {
        if(authService.hasUserEmail(signUpRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User already exists with this email");
        }
        UserDto createdUserDto =  authService.signUp(signUpRequest);
        if(createdUserDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User could not be created");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
    }
}
