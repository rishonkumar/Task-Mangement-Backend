package com.project.Task_SpringBoot.services.auth;

import com.project.Task_SpringBoot.dto.SignUpRequest;
import com.project.Task_SpringBoot.dto.UserDto;
import com.project.Task_SpringBoot.entities.User;
import com.project.Task_SpringBoot.enums.UserRole;
import com.project.Task_SpringBoot.reposistory.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @PostConstruct
    public void createAdminAccount() {
        Optional<User> user = userRepository.findByUserRole(UserRole.ADMIN);
        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setEmail("test@gmail.com");
            newUser.setName("admin");
            newUser.setPassword(new BCryptPasswordEncoder().encode("admin"));
            newUser.setUserRole(UserRole.ADMIN);
            userRepository.save(newUser);
            System.out.println("Admin account created successfully");
        } else {
            System.out.println("Admin account already exists");
        }

    }

    @Override
    public UserDto signUp(SignUpRequest signUpRequest) {
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setName(signUpRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        user.setUserRole(UserRole.EMPLOYEE);
        User createdUser = userRepository.save(user);
        return createdUser.getUserDto();
    }

    @Override
    public boolean hasUserEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}
