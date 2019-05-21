package org.yaodong.telegram.handlers;

import org.yaodong.telegram.dispatch.Context;

public class CommandStartHandler implements Handler {

    @Override
    public void handle(Context context) {
        context.replyText("Welcome!");
    }

}
