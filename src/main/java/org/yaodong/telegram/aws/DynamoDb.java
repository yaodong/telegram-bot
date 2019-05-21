package org.yaodong.telegram.aws;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;

import java.util.Map;

@Component
public class DynamoDb {

    private DynamoDbClient client;

    public DynamoDb() {
        this.client = DynamoDbClient.create();
    }

    public Map<String, AttributeValue> getItem(GetItemRequest request) {
        return client.getItem(request).item();
    }

    public void putItem(PutItemRequest request) {
        client.putItem(request);
    }

    public void updateItem(UpdateItemRequest request) {
        client.updateItem(request);
    }
}
