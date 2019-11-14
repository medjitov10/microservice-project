package com.example.commentsapi.service;

import com.example.commentsapi.model.Comment;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface CommentService {
    Comment createComment(Long postId, String username, Comment comment);

    HttpStatus deleteComment(Long commentId);

    List<Comment> getCommentsByUser(String username);
}
