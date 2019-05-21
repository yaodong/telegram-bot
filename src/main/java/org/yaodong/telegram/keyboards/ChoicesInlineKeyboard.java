package org.yaodong.telegram.keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ChoicesInlineKeyboard {

    public static InlineKeyboardMarkup build(Map<String, String> choices) {
        List<InlineKeyboardButton> buttons = new ArrayList<>(choices.size());
        for (String text : choices.keySet()) {
            String data = choices.get(text);
            buttons.add(new InlineKeyboardButton().setText(text).setCallbackData(data));
        }

        return new InlineKeyboardMarkup()
                .setKeyboard(Collections.singletonList(buttons));
    }
}
