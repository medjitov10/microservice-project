package com.example.commentsapi.service;

import com.example.commentsapi.model.Comment;
import com.example.commentsapi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CommentPostServiceImpl implements CommentPostService {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Comment> getCommentsByPostId(Long postId) {

        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments;
    }

    @Override
    public void deleteCommentsByPostId(Long postId) {
        System.out.println(postId);
        List<Comment> comments = commentRepository.findByPostId(postId);
        System.out.println("=======================================");
        System.out.println("=======================================");
        System.out.println("=======================================");
        System.out.println(comments);
        System.out.println("=======================================");
        System.out.println("=======================================");
        System.out.println("=======================================");

        if(comments.size() > 0)
            commentRepository.deleteAll(comments);
    }
}
