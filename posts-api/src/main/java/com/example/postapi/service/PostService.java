package com.example.postapi.service;

import com.example.postapi.model.Post;

import java.util.List;

public interface PostService {
    Post createPost(String username, Post post);
    Iterable<Post> findAll();
    public void deletePost(Long postId);
    public List<Post> getPostByUser(String username);

    Post getPostByPostId(Long postId);
}
