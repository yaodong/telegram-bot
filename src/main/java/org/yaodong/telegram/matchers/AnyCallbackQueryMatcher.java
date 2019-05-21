package org.yaodong.telegram.matchers;

import org.yaodong.telegram.dispatch.Context;
import org.yaodong.telegram.dispatch.Matcher;

public class AnyCallbackQueryMatcher implements Matcher {

    @Override
    public boolean match(Context context) {
        return context.getUpdate().hasCallbackQuery();
    }

}
