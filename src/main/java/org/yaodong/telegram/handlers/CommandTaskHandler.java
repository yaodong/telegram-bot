package org.yaodong.telegram.handlers;

import org.yaodong.telegram.dispatch.Context;

public class CommandTaskHandler implements Handler {

    @Override
    public void handle(Context context) {
        String text = context.getUpdate().getMessage().getText();
        String content = text.split("\\s+", 2)[1];
        context.getBot().getMailService().send("add-to-things-vbrcacl2hj0extxqjr4@things.email", content, "");
        context.replyText("added to Things.");
    }
}
