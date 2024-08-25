package com.project.Task_SpringBoot.services.admin;


import com.project.Task_SpringBoot.dto.CommentDto;
import com.project.Task_SpringBoot.dto.TaskDto;
import com.project.Task_SpringBoot.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AdminService {


    List<UserDto> getUsers();

    TaskDto createTask(TaskDto taskDto);

    List<TaskDto> getAllTasks();

    void deleteTask(Long id);

    TaskDto getTaskById(Long id);

    TaskDto updateTask(TaskDto taskDto, Long id);

    List<TaskDto> searchTaskByTitle(String title);

    CommentDto createComment(Long taskId, String content);

    List<CommentDto> getCommentsByTaskId(Long taskId);

}
