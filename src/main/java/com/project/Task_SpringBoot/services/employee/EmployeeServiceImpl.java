package com.project.Task_SpringBoot.services.employee;

import com.project.Task_SpringBoot.dto.TaskDto;
import com.project.Task_SpringBoot.entities.Task;
import com.project.Task_SpringBoot.entities.User;
import com.project.Task_SpringBoot.reposistory.TaskRepository;
import com.project.Task_SpringBoot.utils.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final TaskRepository taskRepository;

    private final JwtUtil jwtUtil;

    @Override
    public List<TaskDto> getTasksByUserId() {
        User user = jwtUtil.getLoggedInUser();
        if(user != null) {
           return taskRepository.findAllByUserId(user.getId()).stream().sorted(Comparator.comparing(Task::getDueDate).reversed())
                    .map(Task::getTaskDto).collect(Collectors.toList());
        }
        throw new EntityNotFoundException("User not found");
    }
}
