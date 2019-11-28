package com.example.commentsapi.service;

import com.example.commentsapi.exception.EntityNotFoundException;
import com.example.commentsapi.model.Comment;
import com.example.commentsapi.repository.CommentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CommentPostServiceTest {
    @Mock
    Comment comment;

    @InjectMocks
    CommentPostServiceImpl commentPostService;

    @Mock
    CommentRepository commentRepository;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    List<Comment> comments;
    @Before
    public void initializeDummyData() {
        comment.setId(1L);
        comment.setText("Hello");
        comment.setPostId(1L);
        comment.setUsername("osman");
        comments = Arrays.asList(comment);
//        post.setId(1L);
//        post.setTitle("Title");
//        post.setUser(user);
//        user.setId(1L);
    }

    @Test
    public void getCommentsByPostId_CommentService_Success() throws EntityNotFoundException {
        when(commentRepository.findByPostId(any())).thenReturn(comments);
        assertEquals(commentPostService.getCommentsByPostId(any()), comments);
    }

    @Test
    public void deleteCommentsByPostId_CommentService_Success() throws EntityNotFoundException {
        CommentPostServiceImpl commentPostService = mock(CommentPostServiceImpl.class);
        when(commentRepository.findByPostId(any())).thenReturn(comments);
        doNothing().when(commentRepository).deleteAll(any());
        doNothing().when(commentPostService).deleteCommentsByPostId(any());
        commentPostService.deleteCommentsByPostId(any());
        verify(commentPostService, times(1)).deleteCommentsByPostId(any());
    }

}
