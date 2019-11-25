package com.example.commentsapi.controller;

import com.example.commentsapi.model.Comment;
import com.example.commentsapi.service.CommentPostServiceImpl;
import com.example.commentsapi.service.CommentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.print.attribute.standard.Media;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CommentControllerTest {
    @InjectMocks
    CommentController commentController;
    @InjectMocks
    private Comment comment;

    @Mock
    CommentService commentService;

    @Mock
    CommentPostServiceImpl commentPostService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();
    private String jsonMapper;
    @Test
    public void createComment_commentController_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/1").contentType(MediaType.APPLICATION_JSON)
                .header("username", comment.getUsername())
                .content(createCommentInJson());

        when(commentService.createComment(any(), any(), any())).thenReturn(comment);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(jsonMapper)).andReturn();
    }

    @Test
    public void deleteComment_commentController_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/1").contentType(MediaType.APPLICATION_JSON);
        when(commentService.deleteComment(any())).thenReturn(HttpStatus.OK);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getCommentsByUser_commentController_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("").contentType(MediaType.APPLICATION_JSON)
                .header("username", comment.getUsername());
        List<Comment> listOfComments = new ArrayList<>();
        listOfComments.add(comment);
        String listOfCommentsString = mapper.writeValueAsString(listOfComments);
        when(commentService.getCommentsByUser(any())).thenReturn(listOfComments);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(listOfCommentsString)).andReturn();
    }

    @Test
    public void getCommentsByPostId_CommmentController_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/1/comment").contentType(MediaType.APPLICATION_JSON)
                .header("username", comment.getUsername());
        List<Comment> listOfComments = new ArrayList<>();
        listOfComments.add(comment);
        String listOfCommentsString = mapper.writeValueAsString(listOfComments);
        when(commentPostService.getCommentsByPostId(any())).thenReturn(listOfComments);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(listOfCommentsString))
                .andReturn();
    }
    @Test
    public void deleteCommentsByPostId_CommentsController_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/1/comment").contentType(MediaType.APPLICATION_JSON);
        List<Comment> comments = new ArrayList<>();
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Before
    public void initComment() throws JsonProcessingException {
        String username = "osman";
        comment.setUsername(username);
        comment.setId(1L);
        comment.setPostId(1L);
        comment.setText("hi bye");
        jsonMapper = mapper.writeValueAsString(comment);
    }
    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
    }

    private static String createCommentInJson() {
        return "{\"id\":1,\"text\":\"hi bye\",\"postId\":1,\"user\":{\"username\":\"osman\"}}";
    }
}
