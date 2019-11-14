package com.example.postapi.controller;

import com.example.postapi.model.Post;
import com.example.postapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping
    public Post createPost(@RequestHeader("username") String username, @RequestBody Post post){
        return postService.createPost(username, post);
    }

//    @GetMapping
//    public Post getPost(@)

}
