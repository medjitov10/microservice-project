package com.example.commentsapi.service;

import com.example.commentsapi.model.Comment;
import com.example.commentsapi.model.User;
import com.example.commentsapi.repository.CommentRepository;
import com.example.commentsapi.repository.CommentUserRepository;
import com.example.commentsapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentUserRepository commentUserRepository;

    @Override
    public Comment createComment(Long postId, String username, Comment comment) {
        User user = userRepository.getUserByUsername(username);
        comment.setUser_id(user.getId());
        comment.setPost_id(postId);
        return commentRepository.save(comment);
    }

    @Override
    public HttpStatus deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
        return HttpStatus.OK;
    }

    @Override
    public List<Comment> getCommentsByUser(String username) {
        User user = userRepository.getUserByUsername(username);
        return commentUserRepository.getCommentByUserId(user.getId());
    }
}
