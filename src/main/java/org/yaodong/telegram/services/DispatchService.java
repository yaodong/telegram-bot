package org.yaodong.telegram.services;

import org.yaodong.telegram.configuration.Bot;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface DispatchService {

    void route(Update update, Bot bot);

}
