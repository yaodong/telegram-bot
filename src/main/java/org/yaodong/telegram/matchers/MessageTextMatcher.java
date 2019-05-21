package org.yaodong.telegram.matchers;

import lombok.Getter;
import org.yaodong.telegram.dispatch.Matcher;
import org.yaodong.telegram.dispatch.Message;

import java.util.regex.Pattern;

@Getter
public class MessageTextMatcher implements Matcher {

    private final Pattern pattern;

    public MessageTextMatcher(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public boolean match(Message message) {
        return message.getUpdate().hasMessage() && pattern.matcher(message.getUpdate().getMessage().getText()).matches();
    }

}
