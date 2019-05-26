package org.yaodong.telegram.configuration;

import org.springframework.stereotype.Component;
import org.yaodong.telegram.dispatch.Route;
import org.yaodong.telegram.handlers.*;
import org.yaodong.telegram.matchers.AnyMessageMatcher;
import org.yaodong.telegram.matchers.CommandMatcher;

import java.util.Arrays;
import java.util.List;

@Component
public class Routing {

    public List<Route> routes() {
        return Arrays.asList(
                Route.of(new CommandStartHandler(), new CommandMatcher("start")),
                Route.of(new CommandHelpHandler(), new CommandMatcher("help")),
                Route.of(new CommandTaskHandler(), new CommandMatcher("task")),
                Route.of(new CommandEchoHandler(), new CommandMatcher("echo")),
                Route.of(new PanicHandler(), new AnyMessageMatcher()));
    }
}
