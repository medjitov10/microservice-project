package com.ga.emailservice.mq;

import com.example.commentsapi.model.Comment;
import com.ga.emailservice.service.SMTPService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = "queue1")
public class Receiver {

    @Autowired
    SMTPService sendEmailSMTP;

    @RabbitHandler
    public void receive(Comment comment) throws Exception{
        System.out.println("Message received!!!!!!!");
        System.out.println(comment.toString());
        System.out.println(comment.getText());
//        ObjectMapper mapper = new ObjectMapper();
//        Comment comment = mapper.readValue(commentString, Comment.class);
//        System.out.println(comment.getText());
        // Hi superman, you have received a comment on a post. // header
        // Batman wrote COMMENTTEXT on post TITLE.
//        Post post = postRepository.findById(comment.getPostId()).orElse(null);
//        User postAuthor = null;
//        if (post != null) {
//            postAuthor = userRepository.getUserByUsername(post.getUsername());
//        }
//        String header = "Hi " + postAuthor.getUsername() + ",  you have received a comment on a post.";
//        String body =  comment.getUsername() + " wrote " + comment.getText() + " on post " + post.getTitle() + ".";
//        String receiverEmail = postAuthor.getEmail();
        sendEmailSMTP.setMailServerProperties();
        sendEmailSMTP.sendEmail("Hi superman, you have received a comment on a post.", "Batman wrote COMMENTTEXT on post TITLE.", "pauloneil119@yahoo.com");
//        sendEmailSMTP.sendEmail(header, body, receiverEmail);
    }
}