package com.example.commentsapi.mq;

import com.example.commentsapi.bean.EmailModel;
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
    public void send(EmailModel email) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String commentString = mapper.writeValueAsString(email);
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(queue.getName(), commentString);
        System.out.println("Message sent: " + email + " on q: " + queue.getName());
    }
}