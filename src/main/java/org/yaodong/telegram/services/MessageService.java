package org.yaodong.telegram.services;

import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public interface MessageService {

    void enqueue(List<Update> updates);

    List<Update> dequeue();
}
