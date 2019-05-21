package org.yaodong.telegram.dispatch;

public interface Matcher {
    boolean match(Message message);
}
