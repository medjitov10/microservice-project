package com.ga.emailservice.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ga.emailservice.bean.EmailModel;
import com.ga.emailservice.service.SMTPService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RabbitListener(queues = "queue1")
public class Receiver {

    Logger logger = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    SMTPService sendEmailSMTP;

    @RabbitHandler
    public boolean receive(String stringEmail) throws Exception{
        System.out.println("Message received!!!!!!!");

        ObjectMapper mapper = new ObjectMapper();
        EmailModel emailModel = mapper.readValue(stringEmail, EmailModel.class);

        String header = "Hi " + emailModel.getAuthorUsername() + ",  you have received a comment on a post.";
        String body =  emailModel.getCommentUsername() + " wrote " + emailModel.getCommentText() + " on post " + emailModel.getPostTitle() + ".";
        String receiverEmail = emailModel.getAuthorEmail();
        sendEmailSMTP.setMailServerProperties();
        sendEmailSMTP.sendEmail(header, body, receiverEmail);
        logger.info("Email notification sent successfully");
        return true;
    }
}