package com.project.Task_SpringBoot.services.employee;

import com.project.Task_SpringBoot.dto.TaskDto;

import java.util.List;

public interface EmployeeService {


    List<TaskDto> getTasksByUserId();

    TaskDto updateTask(Long id,String status);
}
