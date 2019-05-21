package org.yaodong.telegram.matchers;

import org.yaodong.telegram.dispatch.Matcher;
import org.yaodong.telegram.dispatch.Message;

public class CatchAllMatcher implements Matcher {

    @Override
    public boolean match(Message message) {
        return true;
    }

}
