package org.yaodong.telegram.dispatch;

import lombok.Getter;
import org.yaodong.telegram.handlers.Handler;

import java.util.Arrays;
import java.util.List;

@Getter
public class Route {

    private final List<Matcher> matchers;

    private final Handler handler;

    private Route(Handler handler, List<Matcher> matchers) {
        this.handler = handler;
        this.matchers = matchers;
    }

    public static Route of(Handler handler, Matcher... matchers) {
        return new Route(handler, Arrays.asList(matchers));
    }

    public boolean match(Message message) {
        for (Matcher matcher : matchers) {
            if (!matcher.match(message)) {
                return false;
            }
        }

        return true;
    }

}
