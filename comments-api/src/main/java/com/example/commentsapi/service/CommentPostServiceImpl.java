package com.example.commentsapi.service;

import com.example.commentsapi.exception.EntityNotFoundException;
import com.example.commentsapi.model.Comment;
import com.example.commentsapi.repository.CommentRepository;
import com.netflix.ribbon.proxy.annotation.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CommentPostServiceImpl implements CommentPostService {
    Logger logger = LoggerFactory.getLogger(CommentPostServiceImpl.class);

    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Comment> getCommentsByPostId(Long postId) throws EntityNotFoundException {
        List<Comment> comments = findCommentsByPostId(postId);
        return comments;
    }

    @Override
    public void deleteCommentsByPostId(Long postId) throws EntityNotFoundException {
        List<Comment> comments = findCommentsByPostId(postId);
        if(comments.size() > 0)
            commentRepository.deleteAll(comments);
    }

    private List<Comment> findCommentsByPostId(Long postId) throws EntityNotFoundException {
        try {
            return commentRepository.findByPostId(postId);
        } catch (Exception e) {
            logger.error("Post ID not found");
            throw new EntityNotFoundException("There is no post with id: " + postId);
        }
    }
}
