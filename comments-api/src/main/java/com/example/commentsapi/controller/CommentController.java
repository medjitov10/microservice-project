package com.example.commentsapi.controller;

import com.example.commentsapi.exception.EntityNotFoundException;
import com.example.commentsapi.model.Comment;
import com.example.commentsapi.mq.Sender;
import com.example.commentsapi.service.CommentPostService;
import com.example.commentsapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    CommentPostService commentPostService;

    @PostMapping("/{postId}")
    public Comment createComment (@PathVariable Long postId, @RequestHeader("username") String username, @RequestBody Comment comment) throws Exception{
        Comment comment1 = commentService.createComment(postId, username, comment);
        return comment1;
    }

    @DeleteMapping("/{commentId}")
    public HttpStatus deleteComment(@PathVariable Long commentId) throws Exception {
        return commentService.deleteComment(commentId);
    }

    @GetMapping
    public List<Comment> getCommentsByUser(@RequestHeader("username") String username) throws EntityNotFoundException {
        return commentService.getCommentsByUser(username);
    }

    @GetMapping("/{postId}/comment")
    public List<Comment> getCommentsByPostId(@PathVariable Long postId) throws EntityNotFoundException {
        return commentPostService.getCommentsByPostId(postId);
    }

    @DeleteMapping("/{postId}/comment")
    public void deleteCommentsByPostId(@PathVariable Long postId) throws EntityNotFoundException {
        commentPostService.deleteCommentsByPostId(postId);
    }
}
