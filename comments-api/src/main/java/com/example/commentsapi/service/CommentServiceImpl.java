package com.example.commentsapi.service;

import com.example.commentsapi.model.Comment;
import com.example.commentsapi.model.EmailModel;
import com.example.commentsapi.model.Post;
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

    @Autowired
    PostService postService;

    @Override
    public Comment createComment(Long postId, String username, Comment comment) throws Exception {
        comment.setUsername(username);
        comment.setPostId(postId);
        commentRepository.save(comment);
        sendEmailHanlder(comment);
        return comment;
    }

    private void sendEmailHanlder(Comment comment) throws Exception {
        Post post = postService.getPostByPostId(comment.getPostId());
        User postUser = userRepository.getUserByUsername(post.getUser().getUsername());
        EmailModel email = new EmailModel();
        email.setAuthorEmail(postUser.getEmail());
        email.setPostTitle(post.getTitle());
        email.setCommentText(comment.getText());
        email.setCommentUsername(comment.getUsername());
        email.setAuthorUsername(post.getUser().getUsername());
        sender.send(email);
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
