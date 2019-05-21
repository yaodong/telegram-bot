package org.yaodong.telegram.handlers;

import org.yaodong.telegram.dispatch.Message;

public interface Handler {
    void handle(Message message);
}
