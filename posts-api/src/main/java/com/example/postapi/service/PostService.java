package com.example.postapi.service;

import com.example.postapi.model.Post;

public interface PostService {
    Post createPost(String username, Post post);
}
