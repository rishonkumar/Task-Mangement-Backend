package com.project.Task_SpringBoot.services.admin;

import com.project.Task_SpringBoot.dto.TaskDto;
import com.project.Task_SpringBoot.dto.UserDto;
import com.project.Task_SpringBoot.entities.Task;
import com.project.Task_SpringBoot.entities.User;
import com.project.Task_SpringBoot.enums.TaskStatus;
import com.project.Task_SpringBoot.enums.UserRole;
import com.project.Task_SpringBoot.reposistory.TaskRepository;
import com.project.Task_SpringBoot.reposistory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {


    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    private final Logger logger = Logger.getLogger(AdminServiceImpl.class.getName());

    @Override
    public List<UserDto> getUsers() {

        logger.info("Inside getUsers");

        return userRepository.findAll().stream().filter(user -> user.getUserRole() == UserRole.EMPLOYEE)

                .map(User::getUserDto).collect(Collectors.toList());
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        Optional<User> optionalUser = userRepository.findById(taskDto.getEmployee());
        if (optionalUser.isPresent()) {
            Task task = new Task();
            task.setTitle(taskDto.getTitle());
            task.setDescription(taskDto.getDescription());
            task.setPriority(taskDto.getPriority());
            task.setDueDate(task.getDueDate());
            task.setTaskStatus(task.getTaskStatus());
            task.setUser(optionalUser.get());
            taskRepository.save(task).getTaskDto();
        }
        return null;
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream().sorted(Comparator.comparing(Task::getDueDate).reversed())
                .map(Task::getTaskDto).collect(Collectors.toList());
    }

    @Override
    public void deleteTask(Long id) {
         taskRepository.deleteById(id);
    }

    @Override
    public TaskDto getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null).getTaskDto();
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto, Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if(optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setTitle(taskDto.getTitle());
            task.setDescription(taskDto.getDescription());
            task.setPriority(taskDto.getPriority());
            task.setDueDate(taskDto.getDueDate());
            task.setTaskStatus(mapStringToTaskStatus(String.valueOf(taskDto.getTaskStatus())));
            task.setPriority(taskDto.getPriority());
            return taskRepository.save(task).getTaskDto();
        }
        return null;
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


}
