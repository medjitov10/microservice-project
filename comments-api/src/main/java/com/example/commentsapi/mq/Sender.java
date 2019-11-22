package com.example.commentsapi.mq;

import com.example.commentsapi.model.Comment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Sender {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    Queue queue;
    public void send(Comment comment) throws Exception{
        comment.setPostId(1L);
        comment.setUsername("qweqwe");
        comment.setId(1L);
        ObjectMapper mapper = new ObjectMapper();
        String commentString = mapper.writeValueAsString(comment);


        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(queue.getName(), commentString);
        System.out.println("Message sent: " + comment + " on q: " + queue.getName());
    }
}