package com.example.commentsapi.controller;

import com.example.commentsapi.exception.EntityNotFoundException;
import com.example.commentsapi.model.Comment;
import com.example.commentsapi.service.CommentPostService;
import com.example.commentsapi.service.CommentService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "create a comment", notes = "authorized user creates a comment on a post", response = Comment.class)
    @PostMapping("/{postId}")
    public Comment createComment (@PathVariable Long postId, @RequestHeader("username") String username, @RequestBody Comment comment) throws Exception{
        Comment comment1 = commentService.createComment(postId, username, comment);
        return comment1;
    }

    @ApiOperation(value = "delete a comment", notes = "authorized user can delete own comment", response = Comment.class)
    @DeleteMapping("/{commentId}")
    public HttpStatus deleteComment(@PathVariable Long commentId) throws Exception {
        return commentService.deleteComment(commentId);
    }

    @ApiOperation(value = "get comments by user", notes = "authorized user can get a list of comments user creates", response = List.class)
    @GetMapping
    public List<Comment> getCommentsByUser(@RequestHeader("username") String username) throws EntityNotFoundException {
        return commentService.getCommentsByUser(username);
    }

    @ApiOperation(value = "get comments by post id", notes = "authorized users can view comments for each post", response = List.class)
    @GetMapping("/{postId}/comment")
    public List<Comment> getCommentsByPostId(@PathVariable Long postId) throws EntityNotFoundException {
        return commentPostService.getCommentsByPostId(postId);
    }

    @ApiOperation(value = "delete comments by post id", notes = "comments are deleted for authorized user who deletes own post", response = void.class)
    @DeleteMapping("/{postId}/comment")
    public void deleteCommentsByPostId(@PathVariable Long postId) throws EntityNotFoundException {
        commentPostService.deleteCommentsByPostId(postId);
    }
}
