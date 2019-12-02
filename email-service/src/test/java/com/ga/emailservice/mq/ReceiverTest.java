package com.ga.emailservice.mq;

import com.ga.emailservice.service.SMTPService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class ReceiverTest {
    @Mock
    SMTPService smtpService;
    @InjectMocks
    Receiver receiver;

    @Test
    public void mq_Success() throws Exception {
        when(smtpService.setMailServerProperties()).thenReturn(true);
        when(smtpService.sendEmail(anyString(), anyString(), anyString())).thenReturn(true);
        receiver.receive("{\"authorEmail\":\"qwe\",\"postTitle\":\"qwe\",\"commentText\":\"qwe\",\"authorUsername\":\"qwe\",\"commentUsername\":\"qwe\"}");
    }
}
