package com.example.postapi.service;

import com.example.postapi.model.Post;
import com.example.postapi.model.User;
import com.example.postapi.repository.PostRepository;
import com.example.postapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;


    @Autowired
    CommentService commentService;
    @Override
    public Post createPost(String username, Post post) {
        post.setUsername(username);
        return postRepository.save(post);
    }

    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public void deletePost(Long postId){
        Post post = postRepository.findById(postId).orElse(null);
        if(post != null) {
            commentService.deleteCommentsByPostId(postId);
            postRepository.delete(post);
        }
    }

    @Override
    public List<Post> getPostByUser(String username) {
        return postRepository.findByUsername(username);
    }
}
