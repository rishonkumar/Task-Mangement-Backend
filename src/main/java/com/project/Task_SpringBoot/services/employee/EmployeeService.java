package com.project.Task_SpringBoot.services.employee;

import com.project.Task_SpringBoot.dto.CommentDto;
import com.project.Task_SpringBoot.dto.TaskDto;

import java.util.List;

public interface EmployeeService {


    List<TaskDto> getTasksByUserId();

    TaskDto updateTask(Long id,String status);

    CommentDto createComment(Long taskId, String content);

    List<CommentDto> getCommentsByTaskId(Long taskId);

    TaskDto getTaskById(Long id);
}
