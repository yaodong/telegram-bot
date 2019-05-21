package org.yaodong.telegram.services;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.yaodong.telegram.aws.SecretsManager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SettingServiceImplTest {

    @Mock
    private SecretsManager secretsManager;

    @InjectMocks
    private SettingServiceImpl settingService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(secretsManager.fetch()).thenReturn(new JSONObject("{\"KEY\":\"VALUE\"}"));
    }

    @Test
    public void testGetString() {
        settingService = new SettingServiceImpl(secretsManager);
        assertEquals("VALUE", settingService.getString("KEY"));
    }

}