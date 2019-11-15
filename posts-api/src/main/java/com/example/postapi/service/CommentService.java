package com.example.postapi.service;

import com.example.postapi.model.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="comments")
public interface CommentService {

    @GetMapping("/{postId}/comment")
    public List<Comment> getCommentsByPostId(@PathVariable Long postId);

    @DeleteMapping("/{postId}/comment")
    void deleteCommentsByPostId(@PathVariable Long postId);
}
