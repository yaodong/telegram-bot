package org.yaodong.telegram.handlers;

import org.yaodong.telegram.dispatch.Message;

public class CommandEchoHandler implements Handler {

    @Override
    public void handle(Message message) {
        String text = message.getUpdate().getMessage().getText();
        message.replyText(text.split("\\s+", 2)[1]);
    }
}
