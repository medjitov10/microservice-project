package com.ga.emailservice.service;

import org.springframework.stereotype.Service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class SMTPService {
    Session mailSession;

    public boolean setMailServerProperties()
    {
        Properties emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", "587");
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
        mailSession = Session.getDefaultInstance(emailProperties, null);
        return true;
    }

    public MimeMessage draftEmailMessage(String header, String body, String email) throws AddressException, MessagingException
    {
        String[] toEmails = { email };
        String emailSubject = header;
        String emailBody = body;
        MimeMessage emailMessage = new MimeMessage(mailSession);
        /**
         * Set the mail recipients
         * */
        for (int i = 0; i < toEmails.length; i++)
        {
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
        }
        emailMessage.setSubject(emailSubject);
        /**
         * If sending HTML mail
         * */
        emailMessage.setContent(emailBody, "text/html");
        /**
         * If sending only text mail
         * */
        //emailMessage.setText(emailBody);// for a text email
        return emailMessage;
    }

    public boolean sendEmail(String header, String body, String email) throws AddressException, MessagingException
    {
        System.out.println("Send Email");
        /**
         * Sender's credentials
         * */
        String fromUser = "paul28680@gmail.com";
        String fromUserEmailPassword = "pauloneil119";

        String emailHost = "smtp.gmail.com";
        Transport transport = mailSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserEmailPassword);
        /**
         * Draft the message
         * */
        MimeMessage emailMessage = draftEmailMessage(header, body, email);
        /**
         * Send the email
         * */
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
        System.out.println("Email sent successfully.");
        return true;
    }
}
