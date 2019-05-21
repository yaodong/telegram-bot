package org.yaodong.telegram.dispatch;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.yaodong.telegram.configuration.Bot;
import org.yaodong.telegram.entities.Profile;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Context {

    private final Bot bot;

    private final Update update;

    private final Profile profile;

    private String callbackQueryName;

    private Map<String, String> callbackQueryParams = new HashMap<>();

    public Context(Update update, Profile profile, Bot bot) {
        this.update = update;
        this.profile = profile;
        this.bot = bot;

        if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            if (callbackData.contains("?")) {
                String[] split = "\\?".split(callbackData, 2);
                this.callbackQueryName = split[0];
                if (split.length > 1) {
                    splitQueryParams(split[1]);
                }
            } else {
                this.callbackQueryName = callbackData;
            }
        }
    }

    private void splitQueryParams(String query) {
        String[] split = "&".split(query, 2);
        for (String s : split) {
            String[] tokens = "=".split(s, 2);
            this.callbackQueryParams.put(tokens[0], tokens[1]);
        }
    }

    public void deleteMessage(Long chatId, Integer messageId) {
        DeleteMessage deleteMessage = new DeleteMessage().setChatId(chatId).setMessageId(messageId);
        try {
            bot.execute(deleteMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void editMessage(Long chatId, Integer messageId, String text) {
        EditMessageText editMessageText = new EditMessageText().setChatId(chatId).setMessageId(messageId).setText(text);
        try {
            bot.execute(editMessageText);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void replyMarkdown(String markdown) {
        SendMessage message = new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText(markdown)
                .enableMarkdown(true);

        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void replyText(String text) {
        SendMessage message = new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText(text);

        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void replyText(String text, InlineKeyboardMarkup inlineKeyboardMarkup) {
        SendMessage message = new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText(text)
                .setReplyMarkup(inlineKeyboardMarkup);

        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
