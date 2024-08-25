package com.project.Task_SpringBoot.services.admin;


import com.project.Task_SpringBoot.dto.TaskDto;
import com.project.Task_SpringBoot.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AdminService {


    List<UserDto> getUsers();

    TaskDto createTask(TaskDto taskDto);


}