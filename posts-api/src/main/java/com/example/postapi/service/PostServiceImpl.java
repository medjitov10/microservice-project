package com.example.postapi.service;

import com.example.postapi.model.Post;
import com.example.postapi.model.User;
import com.example.postapi.repository.PostRepository;
import com.example.postapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Post createPost(String username, Post post) {
        User user = userRepository.getUserByUsername(username);
        post.setUser_id(user.getId());
        return postRepository.save(post);
    }
}
