package com.example.postapi.service;

import com.example.postapi.exception.EntityNotFoundException;
import com.example.postapi.feignClientService.CommentService;
import com.example.postapi.model.Post;
import com.example.postapi.repository.PostRepository;
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
    public void deletePost(Long postId) throws EntityNotFoundException {
        Post post = postRepository.findById(postId).orElse(null);
        if(post != null) {
            commentService.deleteCommentsByPostId(postId);
            postRepository.delete(post);
        } else {
            throw new EntityNotFoundException("Post not found");
        }
    }

    @Override
    public List<Post> getPostByUser(String username) {
        return postRepository.findByUsername(username);
    }

    @Override
    public Post getPostByPostId(Long postId) throws EntityNotFoundException {
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null)
            throw new EntityNotFoundException("Post not found");
        return post;
    }
}
