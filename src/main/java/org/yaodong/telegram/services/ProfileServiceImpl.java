package org.yaodong.telegram.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.yaodong.telegram.aws.DynamoDb;
import org.yaodong.telegram.entities.Profile;
import org.yaodong.telegram.mappers.ProfileMapper;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private DynamoDb client;

    private ProfileMapper mapper;

    private String tableName;

    @Autowired
    public ProfileServiceImpl(DynamoDb client, SettingService setting, ProfileMapper mapper) {
        this.client = client;
        this.mapper = mapper;
        this.tableName = setting.getString("AWS_DYNAMODB_TABLE_NAME");
    }

    @Override
    public Profile fetch(Update update) {
        User user;
        if (update.hasCallbackQuery()) {
            user = update.getCallbackQuery().getFrom();
        } else if (update.hasMessage()) {
            user = update.getMessage().getFrom();
        } else {
            throw new RuntimeException("Unable to parse user");
        }

        Optional<Profile> profile = get(user.getId());
        return profile.orElseGet(() -> create(user.getId(), user.getUserName()));
    }

    private Optional<Profile> get(Integer id) {
        HashMap<String, AttributeValue> getByKey = new HashMap<>();
        getByKey.put("TelegramId", AttributeValue.builder().n(id.toString()).build());

        GetItemRequest request = GetItemRequest.builder()
                .key(getByKey)
                .tableName(tableName)
                .build();

        try {
            Map<String, AttributeValue> response = client.getItem(request);
            if (response.size() == 0) {
                return Optional.empty();
            }

            return Optional.of(mapper.toItem(response));

        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
        }

        return Optional.empty();
    }

    private Profile create(Integer id, String userName) {
        HashMap<String, AttributeValue> item = new HashMap<>();
        item.put("TelegramId", AttributeValue.builder().n(String.valueOf(id)).build());
        item.put("UserName", AttributeValue.builder().s(userName).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build();

        Profile profile = new Profile();
        profile.setId(id);
        profile.setUserName(userName);
        try {
            client.putItem(request);
            return profile;
        } catch (ResourceNotFoundException e) {
            System.err.format("Error: The table can't be found.\n");
            System.err.println("Be sure that it exists and that you've typed its name correctly!");
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
        }
        throw new RuntimeException("Can not create profile.");
    }

    public boolean update(Integer id, String userName) {
        HashMap<String, AttributeValue> item = new HashMap<>();
        item.put("TelegramId", AttributeValue.builder().n(String.valueOf(id)).build());

        HashMap<String, AttributeValueUpdate> updates = new HashMap<>();
        updates.put("UserName", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(userName).build())
                .action(AttributeAction.PUT).build());

        UpdateItemRequest request = UpdateItemRequest.builder()
                .tableName(tableName)
                .key(item)
                .attributeUpdates(updates)
                .build();

        try {
            client.updateItem(request);
            return true;
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }
}
