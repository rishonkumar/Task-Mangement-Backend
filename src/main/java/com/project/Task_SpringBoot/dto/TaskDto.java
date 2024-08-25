package com.project.Task_SpringBoot.dto;

import com.project.Task_SpringBoot.enums.TaskStatus;
import lombok.Data;

import java.util.Date;

@Data
public class TaskDto {

    private long id;

    private String title;

    private String description;

    private Date dueDate;

    private String priority;

    private TaskStatus taskStatus;

    private Long employee;

    private String employeeName;
}
