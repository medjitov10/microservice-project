package com.example.postapi.integration;

import com.example.postapi.model.Post;
import com.example.postapi.repository.PostRepository;
import com.netflix.discovery.converters.Auto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@ActiveProfiles("qa")
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostIntegrationTest {
    @Autowired
    PostRepository postRepository;



    private Post createPost() {
        Post post = new Post();
        post.setUsername("batman");
        post.setTitle("title");
        post.setDescription("Description");
        return post;
    }

    @After
    public void removePost() {
        List<Post> posts = postRepository.findByUsername("batman");
        if(posts != null)
            postRepository.deleteAll(posts);
    }

    @Test
    public void createPost_PostService_Success () {
        Post post = createPost();
        Post savedPost = postRepository.save(post);
        assertNotNull(savedPost);
        assertNotNull(savedPost.getId());

        Post foundPost = postRepository.findById(savedPost.getId()).orElse(null);
        assertNotNull(savedPost);
        assertNotNull(savedPost.getId());
    }

    @Test
    public void createPost_PostService_Exception() {
        Post post = createPost();
        post = postRepository.save(post);
        post.setId(null);
        postRepository.save(post);
    }

    @Test
    public void findAll_PostService_Success() {
        List<Post> posts = (List<Post>) postRepository.findAll();
        assertEquals(posts.size(), 0);

        Post post = createPost();
        post = postRepository.save(post);

        posts = (List<Post>) postRepository.findAll();
        assertEquals(posts.size(), 1);
    }

    @Test
    public void deletePost_PostService_Success() {
        Post post = createPost();
        postRepository.save(post);

        assertNotNull(post.getId());

        postRepository.delete(post);
        post = postRepository.findById(post.getId()).orElse(null);
        assertNull(post);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void deleteNyId_Post_Exception(){
        postRepository.deleteById(1L);
    }

}
