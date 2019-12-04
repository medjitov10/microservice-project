package com.example.postapi.controller;

import com.example.postapi.exception.EntityNotFoundException;
import com.example.postapi.bean.Comment;
import com.example.postapi.model.Post;
import com.example.postapi.feignClientService.CommentService;
import com.example.postapi.service.PostService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "create post", notes = "authorized users can create posts", response = Post.class)
    @PostMapping
    public Post createPost(@RequestHeader("username") String username, @RequestBody Post post){
        System.out.println("fdsafasdfd");
        System.out.println("fdsafasdfd");
        System.out.println("fdsafasdfd");
        System.out.println("fdsafasdfd");
        System.out.println("fdsafasdfd");
        System.out.println("fdsafasdfd");

        return postService.createPost(username, post);
    }

    @ApiOperation(value = "get all posts", notes = "authorized users can view all posts", response = Iterable.class)
    @GetMapping("/all")
    public Iterable<Post> allPosts(){
        return postService.findAll();
    }

    @ApiOperation(value = "delete post", notes = "authorized users can delete own posts", response = HttpStatus.class)
    @DeleteMapping("/{postId}")
    public HttpStatus deletePost(@PathVariable Long postId) throws EntityNotFoundException {
        postService.deletePost(postId);
        return HttpStatus.OK;
    }

    @ApiOperation(value = "get post by user", notes = "authorized users can view posts they created", response = List.class)
    @GetMapping("/user")
    public List<Post> getPostByUser(@RequestHeader("username") String username){
        return postService.getPostByUser(username);
    }

    @ApiOperation(value = "get post by post id", notes = "retrieve a post by its postId", response = Post.class)
    @GetMapping("/{postId}")
    public Post getPostByPostId(@PathVariable Long postId) throws EntityNotFoundException {
        return postService.getPostByPostId(postId);
    }

    @ApiOperation(value = "get comments by postId", notes = "authorized users can get all comments for a post", response = List.class)
    @GetMapping("/{postId}/comment")
    public List<Comment> getCommentsByPostId(@PathVariable Long postId) {
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        return comments;
    }
}
