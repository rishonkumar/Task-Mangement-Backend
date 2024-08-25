package com.project.Task_SpringBoot.controller.Employee;


import com.project.Task_SpringBoot.dto.TaskDto;
import com.project.Task_SpringBoot.services.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController{

    private final EmployeeService employeeService;

    public ResponseEntity<List<TaskDto>>getTaskByUserId() {
        return ResponseEntity.ok(employeeService.getTasksByUserId());
    }
}
