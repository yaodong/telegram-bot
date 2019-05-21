package org.yaodong.telegram.handlers;

import org.yaodong.telegram.dispatch.Context;

public class CommandEchoHandler implements Handler {

    @Override
    public void handle(Context context) {
        String text = context.getUpdate().getMessage().getText();
        context.replyText(text.split("\\s+", 2)[1]);
    }
}
