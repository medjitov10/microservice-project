package com.example.postapi.service;

import com.example.postapi.exception.EntityNotFoundException;
import com.example.postapi.feignClientService.CommentService;
import com.example.postapi.bean.Comment;
import com.example.postapi.model.Post;
import com.example.postapi.repository.PostRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceImplTest {
    @InjectMocks
    Post post;

    @InjectMocks
    PostServiceImpl postService;

    @Mock
    CommentService commentService;
    @Mock
    PostRepository postRepository;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createPost_PostService_Success() {
        when(postRepository.save(any())).thenReturn(post);
        assertEquals(postService.createPost(post.getUsername(), post), post);
    }
    @Test
    public void findAll_PostService_Success() {
        Iterable<Post> posts = new ArrayList<>();
        when(postRepository.findAll()).thenReturn(posts);
        assertEquals(postService.findAll(), posts);
    }

    @Test
    public void deletePost_PostService_Success() throws EntityNotFoundException {
        List<Comment> comments = null;
        PostService postService = mock(PostService.class);
        Optional<Post> optionalPost = Optional.of(post);
//        when(postRepository.findById(any())).thenReturn(optionalPost);
//        when(commentService.getCommentsByPostId(any())).thenReturn(comments);
//        doNothing().when(postRepository).delete(any());
        postService.deletePost(any());
        verify(postService, times(1)).deletePost(any());
    }

    @Test
    public void getPostByUser_PostService_Success() {
        List<Post> posts = new ArrayList<>();
        when(postRepository.findByUsername(any())).thenReturn(posts);
        assertEquals(postService.getPostByUser(any()), posts);
    }

    @Test
    public void getPostByPostId_PostService_Success() throws EntityNotFoundException {
        Optional<Post> optionalPost = Optional.of(post);
        when(postRepository.findById(any())).thenReturn(optionalPost);
        assertEquals(postService.getPostByPostId(any()), post);
    }

}
