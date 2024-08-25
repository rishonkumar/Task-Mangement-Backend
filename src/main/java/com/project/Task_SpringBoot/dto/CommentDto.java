package com.project.Task_SpringBoot.dto;


import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {

    private Long id;

    private String content;

    private Date createdAt;

    private Long taskId;

    private String postedBy;


}
