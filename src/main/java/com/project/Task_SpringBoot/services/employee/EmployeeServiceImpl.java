package com.project.Task_SpringBoot.services.employee;

import com.project.Task_SpringBoot.dto.CommentDto;
import com.project.Task_SpringBoot.dto.TaskDto;
import com.project.Task_SpringBoot.entities.Comment;
import com.project.Task_SpringBoot.entities.Task;
import com.project.Task_SpringBoot.entities.User;
import com.project.Task_SpringBoot.enums.TaskStatus;
import com.project.Task_SpringBoot.reposistory.CommentRepository;
import com.project.Task_SpringBoot.reposistory.TaskRepository;
import com.project.Task_SpringBoot.reposistory.UserRepository;
import com.project.Task_SpringBoot.utils.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class EmployeeServiceImpl implements EmployeeService {

    private final TaskRepository taskRepository;

    private final JwtUtil jwtUtil;


    private final CommentRepository commentRepository;

    @Override
    public List<TaskDto> getTasksByUserId() {
        User user = jwtUtil.getLoggedInUser();
        if(user != null) {
           return taskRepository.findAllByUserId(user.getId()).stream().sorted(Comparator.comparing(Task::getDueDate).reversed())
                    .map(Task::getTaskDto).collect(Collectors.toList());
        }
        throw new EntityNotFoundException("User not found");
    }

    @Override
    public TaskDto updateTask(Long id, String status) {
        Optional<Task> optionalTask  = taskRepository.findById(id);
        if(optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setTaskStatus(mapStringToTaskStatus(status));
            return taskRepository.save(task).getTaskDto();
        }
        throw new EntityNotFoundException("Task not found");
    }

    private TaskStatus mapStringToTaskStatus(String taskStatus) {
        return switch (taskStatus) {
            case "PENDING" -> TaskStatus.PENDING;
            case "COMPLETED" -> TaskStatus.COMPLETED;
            case "INPROGRESS" -> TaskStatus.INPROGRESS;
            case "DEFERRED" -> TaskStatus.DEFERRED;
            default -> TaskStatus.CANCELLED;
        };
    }

    @Override
    public TaskDto getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null).getTaskDto();
    }

    @Override
    public List<CommentDto> getCommentsByTaskId(Long taskId) {
        return commentRepository.findAllByTaskId(taskId).stream().map(Comment::getCommentDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto createComment(Long taskId, String content) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        User user = jwtUtil.getLoggedInUser();

        if ((optionalTask.isPresent()) && user != null) {
            Comment comment = new Comment();
            comment.setCreatedAt(new Date());
            comment.setContent(content);
            comment.setTask(optionalTask.get());
            comment.setUser(user);
            return commentRepository.save(comment).getCommentDto();
        }
        throw new EntityNotFoundException("Comment Not Found");
    }
}
