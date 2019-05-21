package org.yaodong.telegram.matchers;

import lombok.Getter;
import org.yaodong.telegram.dispatch.Matcher;
import org.yaodong.telegram.dispatch.Context;

import java.util.regex.Pattern;

@Getter
public class MessageTextMatcher implements Matcher {

    private final Pattern pattern;

    public MessageTextMatcher(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public boolean match(Context context) {
        return context.getUpdate().hasMessage() && pattern.matcher(context.getUpdate().getMessage().getText()).matches();
    }

}
