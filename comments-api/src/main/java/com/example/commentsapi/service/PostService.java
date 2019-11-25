package com.example.commentsapi.service;

import com.example.commentsapi.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="posts")
public interface PostService {
    @GetMapping("/{postId}")
    public Post getPostByPostId(@PathVariable Long postId);
}


