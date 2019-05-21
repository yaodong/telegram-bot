package org.yaodong.telegram.handlers;

import org.yaodong.telegram.dispatch.Context;

public interface Handler {
    void handle(Context context);
}
