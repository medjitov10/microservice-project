package com.example.postapi.service;

import com.example.postapi.exception.EntityNotFoundException;
import com.example.postapi.feignClientService.CommentService;
import com.example.postapi.model.Post;
import com.example.postapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PostServiceImpl implements PostService {
    Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

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
            logger.error("post not found");
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

        if (post == null) {
            logger.error("post not found");
            throw new EntityNotFoundException("Post not found");
        }
        return post;
    }
}
