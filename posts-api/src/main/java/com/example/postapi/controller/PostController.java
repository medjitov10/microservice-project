package com.example.postapi.controller;

import com.example.postapi.exception.EntityNotFoundException;
import com.example.postapi.bean.Comment;
import com.example.postapi.model.Post;
import com.example.postapi.feignClientService.CommentService;
import com.example.postapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @PostMapping
    public Post createPost(@RequestHeader("username") String username, @RequestBody Post post){
        return postService.createPost(username, post);
    }

    @GetMapping("/all")
    public Iterable<Post> allPosts(){
        return postService.findAll();
    }

    @DeleteMapping("/{postId}")
    public HttpStatus deletePost(@PathVariable Long postId) throws EntityNotFoundException {
        postService.deletePost(postId);
        return HttpStatus.OK;
    }

    @GetMapping("/user")
    public List<Post> getPostByUser(@RequestHeader("username") String username){
        return postService.getPostByUser(username);
    }

    @GetMapping("/{postId}")
    public Post getPostByPostId(@PathVariable Long postId) throws EntityNotFoundException {
        return postService.getPostByPostId(postId);
    }


    @GetMapping("/{postId}/comment")
    public List<Comment> getCommentsByPostId(@PathVariable Long postId) {
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        return comments;
    }
}
