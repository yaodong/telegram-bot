package org.yaodong.telegram.services;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.yaodong.telegram.aws.SimpleQueue;

import static org.junit.Assert.*;

@SpringBootTest
public class MessageServiceImplTest {

    @Mock
    private SimpleQueue queue;

    @InjectMocks
    private MessageServiceImpl service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }

}