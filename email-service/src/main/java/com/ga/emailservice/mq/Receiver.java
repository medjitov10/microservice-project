package com.ga.emailservice.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ga.emailservice.bean.EmailModel;
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
    public boolean receive(String stringEmail) throws Exception{
        System.out.println("Message received!!!!!!!");

        ObjectMapper mapper = new ObjectMapper();
        EmailModel emailModel = mapper.readValue(stringEmail, EmailModel.class);

        String header = "Hi " + emailModel.getAuthorUsername() + ",  you have received a comment on a post.";
        String body =  emailModel.getCommentUsername() + " wrote " + emailModel.getCommentText() + " on post " + emailModel.getPostTitle() + ".";
        String receiverEmail = emailModel.getAuthorEmail();
        sendEmailSMTP.setMailServerProperties();
        sendEmailSMTP.sendEmail(header, body, receiverEmail);
        return true;
    }
}