package org.yaodong.telegram.handlers;

import org.yaodong.telegram.dispatch.Context;

import java.util.Random;

public class PanicHandler implements Handler {

    private Random random;

    private String[] texts = new String[]{
            "I'm sorry, I didn't understand that.",
            "Hmm?",
            "Excuse me?",
    };

    public PanicHandler() {
        this.random = new Random();
    }

    @Override
    public void handle(Context context) {
        int index = random.nextInt(texts.length);
        context.replyText(texts[index]);
    }
}
