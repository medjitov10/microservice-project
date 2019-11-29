package com.example.commentsapi.service;

import com.example.commentsapi.exception.EntityNotFoundException;
import com.example.commentsapi.exception.InvalidInputException;
import com.example.commentsapi.feignClientService.PostService;
import com.example.commentsapi.model.Comment;
import com.example.commentsapi.bean.EmailModel;
import com.example.commentsapi.bean.Post;
import com.example.commentsapi.bean.User;
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

    @Autowired
    PostService postService;

    @Override
    public Comment createComment(Long postId, String username, Comment comment) throws Exception, InvalidInputException {
        if (comment.getText().length() < 1)
            throw new InvalidInputException("Text must contain at least 1 character.");
        comment.setUsername(username);
        comment.setPostId(postId);
        commentRepository.save(comment);
        sendEmailHandler(comment);
        return comment;
    }

    public void sendEmailHandler(Comment comment) throws Exception {
        Post post = postService.getPostByPostId(comment.getPostId());
        User postUser = userRepository.getUserByUsername(post.getUser().getUsername());
        EmailModel email = new EmailModel(
                postUser.getEmail(), post.getTitle(), comment.getText(),
                comment.getUsername(), post.getUser().getUsername()
        );
        sender.send(email);
    }

    @Override
    public HttpStatus deleteComment(Long commentId) throws Exception {
        try {
            commentRepository.deleteById(commentId);
        } catch (Exception e) {
            throw new Exception("Unable to delete comment with id: "+ commentId + ".");
        }
        return HttpStatus.OK;
    }

    @Override
    public List<Comment> getCommentsByUser(String username) throws EntityNotFoundException {
        try {
            return commentRepository.findByUsername(username);
        } catch (Exception e) {
            throw new EntityNotFoundException("Invalid username");
        }
    }
}
