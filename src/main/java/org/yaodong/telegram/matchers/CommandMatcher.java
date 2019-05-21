package org.yaodong.telegram.matchers;

import org.yaodong.telegram.dispatch.Matcher;
import org.yaodong.telegram.dispatch.Message;

public class CommandMatcher implements Matcher {

    private final String name;

    public CommandMatcher(String name) {
        this.name = name;
    }

    @Override
    public boolean match(Message message) {
        if (!message.getUpdate().hasMessage()) {
            return false;
        }

        String text = message.getUpdate().getMessage().getText();
        return text.startsWith("/" + name);
    }
}
