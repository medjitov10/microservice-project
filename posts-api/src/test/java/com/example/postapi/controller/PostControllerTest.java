package com.example.postapi.controller;

import com.example.postapi.bean.Comment;
import com.example.postapi.model.Post;
import com.example.postapi.bean.User;
import com.example.postapi.feignClientService.CommentService;
import com.example.postapi.service.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class PostControllerTest {
    @InjectMocks
    PostController postController;
    @InjectMocks
    Post post;

    @InjectMocks
    User user;

    @InjectMocks
    Comment comment;

    @Mock
    CommentService commentService;

    @Mock
    PostService postService;

    @Autowired
    private MockMvc mockMvc;
    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    }

    @Before
    public void createDummyPost() throws JsonProcessingException {
        post.setId(1L);
        post.setUsername("osman");
        post.setDescription("hi hi");
        post.setTitle("Bye bye");
        comment.setId(1L);
        comment.setPostId(1l);
        comment.setText("hi");
        comment.setUser(user);
        comment.setUsername("osman");
        jsonMapper = mapper.writeValueAsString(post);
    }

    private ObjectMapper mapper = new ObjectMapper();
    private String jsonMapper;

    @Test
    public void createPost_PostController_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/").contentType(MediaType.APPLICATION_JSON)
                .header("username", post.getUsername())
                .content(createPostInJson());

        when(postService.createPost(any(), any())).thenReturn(post);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(jsonMapper)).andReturn();
    }

    @Test
    public void allPost_PostController_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/all").contentType(MediaType.APPLICATION_JSON);

        List<Post> listOfPosts = new ArrayList<>();
        listOfPosts.add(post);
        String listOfCommentsString = mapper.writeValueAsString(listOfPosts);

        when(postService.findAll()).thenReturn(listOfPosts);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(listOfCommentsString)).andReturn();
    }

    @Test
    public void deletePost_PostController_Succes() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/1").contentType(MediaType.APPLICATION_JSON);

        doNothing().when(postService).deletePost(any());

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getPostByUser_PostController_Succes() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/user").contentType(MediaType.APPLICATION_JSON)
                .header("username", post.getUsername());

        List<Post> listOfPosts = new ArrayList<>();
        listOfPosts.add(post);
        String listOfCommentsString = mapper.writeValueAsString(listOfPosts);

        when(postService.getPostByUser(any())).thenReturn(listOfPosts);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(listOfCommentsString)).andReturn();
    }

    @Test
    public void getPostByPostId_PostController_Succes() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/1").contentType(MediaType.APPLICATION_JSON)
                .header("username", post.getUsername());


        when(postService.getPostByPostId(any())).thenReturn(post);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(jsonMapper)).andReturn();
    }

    @Test
    public void getCommentsByUser_PostController_Succes() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/1/comment").contentType(MediaType.APPLICATION_JSON);

        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        String listOfCommentsString = mapper.writeValueAsString(comments);

        when(commentService.getCommentsByPostId(any())).thenReturn(comments);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(listOfCommentsString)).andReturn();
    }

    private String createPostInJson() {
        return "{\"id\":1,\"title\":\"Bye bye\",\"description\":\"hi hi\",\"user\":{\"username\":\"osman\"}}";
    }

}
