package com.project.Task_SpringBoot.controller.admin;


import com.project.Task_SpringBoot.dto.TaskDto;
import com.project.Task_SpringBoot.services.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<?>getUsers() {
        return ResponseEntity.ok(adminService.getUsers());
    }

    @PostMapping("/task")
    public ResponseEntity<TaskDto>createTask(@RequestBody TaskDto taskDto) {
        TaskDto task = adminService.createTask(taskDto);
        if (task == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

}
