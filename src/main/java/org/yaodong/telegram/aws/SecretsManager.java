package org.yaodong.telegram.aws;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

@Component
public class SecretsManager {

    private SecretsManagerClient client;

    private String name;

    public SecretsManager(@Value("${AWS_SECRET_NAME}") String name) {
        this.name = name;
        this.client = SecretsManagerClient.create();
    }

    public JSONObject fetch() {
        GetSecretValueRequest request = GetSecretValueRequest.builder().secretId(name).build();
        GetSecretValueResponse response = client.getSecretValue(request);
        return new JSONObject(response.secretString());
    }
}
