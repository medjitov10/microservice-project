package com.example.commentsapi.service;

import com.example.commentsapi.exception.EntityNotFoundException;
import com.example.commentsapi.model.Comment;

import java.util.List;

public interface CommentPostService {
    public List<Comment> getCommentsByPostId(Long postId) throws EntityNotFoundException;

    void deleteCommentsByPostId(Long postId) throws EntityNotFoundException;
}
