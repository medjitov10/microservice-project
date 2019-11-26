package com.example.commentsapi.service;

import com.example.commentsapi.exception.EntityNotFoundException;
import com.example.commentsapi.model.Comment;
import com.example.commentsapi.model.EmailModel;
import com.example.commentsapi.model.Post;
import com.example.commentsapi.model.User;
import com.example.commentsapi.mq.Sender;
import com.example.commentsapi.repository.CommentRepository;
import com.example.commentsapi.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CommentServiceTest {

    @InjectMocks
    Comment comment;
    @InjectMocks
    EmailModel email;
    @InjectMocks
    Post post;
    @InjectMocks
    User user;
    @Mock
    Sender sender;
    @InjectMocks
    CommentServiceImpl commentService;

    @Mock
    PostService postService;

    @Mock
    CommentRepository commentRepository;
    @Mock
    UserRepository userRepository;


    @Before
    public void initializeDummyData() {
        comment.setId(1L);
        comment.setText("Hello");
        comment.setPostId(1L);
        comment.setUsername("osman");
        post.setId(1L);
        post.setTitle("Title");
        post.setUser(user);
        user.setId(1L);
    }

    @Test
    public void createComment_CommentService_Success() throws Exception {
        when(commentRepository.save(comment)).thenReturn(comment);
        when(postService.getPostByPostId(any())).thenReturn(post);
        when(userRepository.getUserByUsername(any())).thenReturn(user);
        Mockito.doNothing().when(sender).send(email);
        assertEquals(commentService.createComment(1L, "osman", comment), comment);
    }

    @Test
    public void deleteComment_CommentService_Success() throws Exception {
        Mockito.doNothing().when(commentRepository).deleteById(any());
        assertEquals(commentService.deleteComment(1L), HttpStatus.OK);
    }

    @Test
    public void getCommentsByUser_CommentService_Success() throws EntityNotFoundException {
        List<Comment> comments = Arrays.asList(comment);
        when(commentRepository.findByUsername(any())).thenReturn(comments);
        assertEquals(commentService.getCommentsByUser(any()), comments);
    }

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
}
