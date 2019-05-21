package org.yaodong.telegram.services;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.yaodong.telegram.aws.DynamoDb;
import org.yaodong.telegram.entities.Profile;
import org.yaodong.telegram.mappers.ProfileMapper;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProfileServiceImplTest {

    @Mock
    private DynamoDb client;

    @Mock
    private SettingService setting;

    @Mock
    private ProfileMapper mapper;

    @InjectMocks
    private ProfileServiceImpl profileService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        int telegramId = 123456;
        String telegramName = "Mock";
        String tableName = "Profile_Table";

        HashMap<String, AttributeValue> mockResponse = new HashMap<>();
        mockResponse.put("TelegramId", AttributeValue.builder().n(Integer.toString(telegramId)).build());
        mockResponse.put("Name", AttributeValue.builder().s(telegramName).build());

        when(client.getItem(any(GetItemRequest.class))).thenReturn(mockResponse);

        doNothing().when(client).putItem(any(PutItemRequest.class));
        doNothing().when(client).updateItem(any(UpdateItemRequest.class));

        when(setting.getString(anyString())).thenReturn(tableName);

        Profile profile = new Profile();
        profile.setId(telegramId);
        profile.setUserName(telegramName);

        when(mapper.toItem(any())).thenReturn(profile);
    }


}