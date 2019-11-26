package com.example.commentsapi.service;

import com.example.commentsapi.exception.EntityNotFoundException;
import com.example.commentsapi.model.Comment;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface CommentService {
    Comment createComment(Long postId, String username, Comment comment) throws Exception;

    HttpStatus deleteComment(Long commentId) throws Exception;

    List<Comment> getCommentsByUser(String username) throws EntityNotFoundException;
}
