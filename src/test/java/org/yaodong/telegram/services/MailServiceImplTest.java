package org.yaodong.telegram.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.yaodong.telegram.aws.SimpleEmail;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;

public class MailServiceImplTest {

    @Mock
    private SimpleEmail simpleEmail;

    @InjectMocks
    private MailServiceImpl mailService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        doNothing().when(simpleEmail).send(anyString(), anyString(), anyString());
    }

    @Test
    public void testSend() {
        mailService.send("name@example.com", "subject", "body");
    }

}