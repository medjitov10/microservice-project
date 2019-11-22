package com.example.commentsapi.service;

import com.example.commentsapi.model.Comment;
import com.example.commentsapi.model.User;
import com.example.commentsapi.mq.Sender;
import com.example.commentsapi.repository.CommentRepository;
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
    Sender sender;

    @Override
    public Comment createComment(Long postId, String username, Comment comment) throws Exception {
        comment.setUsername(username);
        comment.setPostId(postId);
        commentRepository.save(comment);
        sender.send(comment);
        return comment;
    }

    @Override
    public HttpStatus deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
        return HttpStatus.OK;
    }

    @Override
    public List<Comment> getCommentsByUser(String username) {
        return commentRepository.findByUsername(username);
    }
}
