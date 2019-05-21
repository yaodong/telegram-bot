package org.yaodong.telegram.services;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaodong.telegram.aws.SecretsManager;

@Service
public class SettingServiceImpl implements SettingService {

    private JSONObject data;

    @Autowired
    public SettingServiceImpl(SecretsManager secretsManager) {
        this.data = secretsManager.fetch();
    }

    @Override
    public String getString(String key) {
        return data.getString(key);
    }

}
