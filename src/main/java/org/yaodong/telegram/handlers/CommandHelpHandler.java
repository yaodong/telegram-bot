package org.yaodong.telegram.handlers;

import org.yaodong.telegram.dispatch.Context;

public class CommandHelpHandler implements Handler {

    @Override
    public void handle(Context context) {
        context.replyMarkdown("**Commands:**\n" +
                "/task add a task to things");
    }
}
