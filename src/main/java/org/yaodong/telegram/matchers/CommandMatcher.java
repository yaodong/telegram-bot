package org.yaodong.telegram.matchers;

import org.yaodong.telegram.dispatch.Matcher;
import org.yaodong.telegram.dispatch.Context;

public class CommandMatcher implements Matcher {

    private final String name;

    public CommandMatcher(String name) {
        this.name = name;
    }

    @Override
    public boolean match(Context context) {
        if (!context.getUpdate().hasMessage()) {
            return false;
        }

        String text = context.getUpdate().getMessage().getText();
        return text.startsWith("/" + name);
    }
}
