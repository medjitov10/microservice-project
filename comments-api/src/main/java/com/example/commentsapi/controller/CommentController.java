package com.example.commentsapi.controller;

import com.example.commentsapi.model.Comment;
import com.example.commentsapi.repository.CommentRepository;
import com.example.commentsapi.service.CommentService;
import com.example.commentsapi.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/{postId}")
    public Comment createComment(@PathVariable Long postId, @RequestHeader("username") String username, @RequestBody Comment comment) {
        return commentService.createComment(postId, username, comment);
    }

    @DeleteMapping("/{commentId}")
    public HttpStatus deleteComment(@PathVariable Long commentId) {
        return commentService.deleteComment(commentId);
    }

    @GetMapping
    public List<Comment> getCommentsByUser(@RequestHeader("username") String username) {
        return commentService.getCommentsByUser(username);
    }
}
