package org.yaodong.telegram.matchers;

import org.yaodong.telegram.dispatch.Matcher;
import org.yaodong.telegram.dispatch.Context;

public class CatchAllMatcher implements Matcher {

    @Override
    public boolean match(Context context) {
        return true;
    }

}
