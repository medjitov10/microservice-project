package com.ga.emailservice.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(SMTPService.class)
public class SMTPServiceTest {
    @InjectMocks
    SMTPService smtpService;

    @Test
    public void setEmail_SMTPService_Success() {
        assertEquals(smtpService.setMailServerProperties(), true);
    }

    @Test
    public void draftEmailMessage_SmtpServerService_Success() throws MessagingException {
        MimeMessage emailTarget = smtpService.draftEmailMessage("email", "qwe", "qwe");
        assertNotNull(emailTarget);
    }
}
