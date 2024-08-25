package com.project.Task_SpringBoot.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.Task_SpringBoot.dto.CommentDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;


    private Date createdAt;

    private Long taskId;

    private String postedBy;

    private User user;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Task task;

    public CommentDto getCommentDto() {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(id);
        commentDto.setContent(content);
        commentDto.setCreatedAt(createdAt);
        commentDto.setPostedBy(postedBy);
        commentDto.setTaskId(taskId);
        return commentDto;
    }


}
