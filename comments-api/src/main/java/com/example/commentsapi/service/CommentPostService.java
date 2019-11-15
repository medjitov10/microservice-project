package com.example.commentsapi.service;

import com.example.commentsapi.model.Comment;

import java.util.List;

public interface CommentPostService {
    public List<Comment> getCommentsByPostId(Long postId);

    void deleteCommentsByPostId(Long postId);
}
