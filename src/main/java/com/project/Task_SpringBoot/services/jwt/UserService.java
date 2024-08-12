package com.project.Task_SpringBoot.services.jwt;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    UserDetailsService userDetailService();

}
