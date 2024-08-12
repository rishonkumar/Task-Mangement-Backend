package com.project.Task_SpringBoot.services.auth;

import com.project.Task_SpringBoot.dto.SignUpRequest;
import com.project.Task_SpringBoot.dto.UserDto;

public interface AuthService {

    UserDto signUp(SignUpRequest signUpRequest);

    boolean hasUserEmail(String email);
}
