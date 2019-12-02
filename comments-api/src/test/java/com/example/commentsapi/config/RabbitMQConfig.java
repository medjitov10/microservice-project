package com.example.commentsapi.config;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.Queue;

import static org.junit.Assert.assertEquals;

public class RabbitMQConfig {
    Queue queue;
    private final String q = "qwe";
    @Before
    public void init() {
        queue = new Queue(q, false, false, false);
    }
    @Test
    public void queue_RabbitMqConfig_Success() {
        assertEquals(q, queue.getName());
    }
}
