package com.example.commentsapi.feignClientService;

import com.example.commentsapi.bean.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="posts")
public interface PostService {
    @GetMapping("/{postId}")
    public Post getPostByPostId(@PathVariable Long postId);
}


