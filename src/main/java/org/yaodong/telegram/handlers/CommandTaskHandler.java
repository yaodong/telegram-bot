package org.yaodong.telegram.handlers;

import org.yaodong.telegram.dispatch.Message;

public class CommandTaskHandler implements Handler {

    @Override
    public void handle(Message message) {
        String text = message.getUpdate().getMessage().getText();
        String content = text.split("\\s+", 2)[1];
        message.getBot().getMailService().send("add-to-things-vbrcacl2hj0extxqjr4@things.email", content, "");
        message.replyText("added to Things.");
    }
}
