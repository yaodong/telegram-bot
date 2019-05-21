package org.yaodong.telegram.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.yaodong.telegram.services.DispatchService;
import org.yaodong.telegram.services.MailService;
import org.yaodong.telegram.services.MessageService;
import org.yaodong.telegram.services.SettingService;

import java.util.Collections;
import java.util.List;

@Getter
@Component
public class Bot extends TelegramLongPollingBot {

    private SettingService settingService;

    private DispatchService dispatchService;

    private MessageService messageService;

    private MailService mailService;

    @Autowired
    public Bot(SettingService settingService,
               DispatchService dispatchService,
               MessageService messageService,
               MailService mailService) {
        this.settingService = settingService;
        this.messageService = messageService;
        this.dispatchService = dispatchService;
        this.mailService = mailService;
    }

    public void onUpdateReceived(Update update) {
        onUpdatesReceived(Collections.singletonList(update));
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        messageService.enqueue(updates);
    }

    @Scheduled(fixedRate = 1000, initialDelay = 0)
    public void processMessages() {
        List<Update> updates = messageService.dequeue();
        for (Update update : updates) {
            dispatchService.route(update, this);
        }
    }

    public String getBotUsername() {
        return settingService.getString("TELEGRAM_BOT_NAME");
    }

    public String getBotToken() {
        return settingService.getString("TELEGRAM_BOT_TOKEN");
    }

}
