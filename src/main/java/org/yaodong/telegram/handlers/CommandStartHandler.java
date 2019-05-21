package org.yaodong.telegram.handlers;

import org.yaodong.telegram.dispatch.Message;

public class CommandStartHandler implements Handler {

    @Override
    public void handle(Message message) {
        message.replyText("Welcome!");
    }

}
