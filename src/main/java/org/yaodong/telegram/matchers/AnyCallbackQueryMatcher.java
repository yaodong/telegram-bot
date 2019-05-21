package org.yaodong.telegram.matchers;

import org.yaodong.telegram.dispatch.Message;
import org.yaodong.telegram.dispatch.Matcher;

public class AnyCallbackQueryMatcher implements Matcher {

    @Override
    public boolean match(Message message) {
        return message.getUpdate().hasCallbackQuery();
    }

}
