package com.project.Task_SpringBoot.reposistory;

import com.project.Task_SpringBoot.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
