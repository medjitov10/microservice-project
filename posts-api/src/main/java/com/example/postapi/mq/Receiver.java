package com.example.postapi.mq;

import com.example.postapi.model.Comment;
import com.example.postapi.model.Post;
import com.example.postapi.model.User;
import com.example.postapi.repository.PostRepository;
import com.example.postapi.repository.UserRepository;
import com.example.postapi.service.SendEmailSMTP;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = "queue1")
public class Receiver {
    @Autowired
    SendEmailSMTP sendEmailSMTP;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @RabbitHandler
    public void receive(String commentString) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Comment comment = mapper.readValue(commentString, Comment.class);
        // Hi superman, you have received a comment on a post. // header
        // Batman wrote COMMENTTEXT on post TITLE.
        Post post = postRepository.findById(comment.getPostId()).orElse(null);
        User postAuthor = null;
        if (post != null) {
            postAuthor = userRepository.getUserByUsername(post.getUsername());
        }
        String header = "Hi " + postAuthor.getUsername() + ",  you have received a comment on a post.";
        String body =  comment.getUsername() + " wrote " + comment.getText() + " on post " + post.getTitle() + ".";
        String receiverEmail = postAuthor.getEmail();
        sendEmailSMTP.setMailServerProperties();
        sendEmailSMTP.sendEmail("Hi superman, you have received a comment on a post.", "Batman wrote COMMENTTEXT on post TITLE.", "pauloneil119@yahoo.com");
//        sendEmailSMTP.sendEmail(header, body, receiverEmail);
    }
}