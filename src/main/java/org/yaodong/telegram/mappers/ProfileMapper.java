package org.yaodong.telegram.mappers;

import org.springframework.stereotype.Component;
import org.yaodong.telegram.entities.Profile;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

@Component
public class ProfileMapper {

    public Profile toItem(Map<String, AttributeValue> attributes) {

        Profile item = new Profile();
        item.setId(Integer.valueOf(attributes.get("TelegramId").n()));

        AttributeValue email = attributes.get("UserName");
        if (email != null) {
            item.setUserName(email.s());
        }

        AttributeValue validated = attributes.get("Validated");
        if (validated != null) {
            item.setValidated(validated.bool());
        }

        AttributeValue activated = attributes.get("Activated");
        if (activated != null) {
            item.setActivated(activated.bool());
        }

        return item;
    }

}
