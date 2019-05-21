package org.yaodong.telegram.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.yaodong.telegram.configuration.Bot;
import org.yaodong.telegram.dispatch.Context;
import org.yaodong.telegram.dispatch.Route;
import org.yaodong.telegram.entities.Profile;
import org.yaodong.telegram.handlers.*;
import org.yaodong.telegram.matchers.AnyMessageMatcher;
import org.yaodong.telegram.matchers.CommandMatcher;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class DispatchServiceImpl implements DispatchService {

    private ProfileService profileService;

    private static List<Route> handlers = Arrays.asList(
            Route.of(new CommandStartHandler(), new CommandMatcher("start")),
            Route.of(new CommandTaskHandler(), new CommandMatcher("task")),
            Route.of(new CommandEchoHandler(), new CommandMatcher("echo")),
            Route.of(new PanicHandler(), new AnyMessageMatcher()));

    @Autowired
    public DispatchServiceImpl(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Override
    public void route(Update update, Bot bot) {
        Profile profile = profileService.fetch(update);
        Context context = new Context(update, profile, bot);
        findHandler(context).ifPresent((h) -> h.handle(context));
    }

    private Optional<Handler> findHandler(Context context) {
        for (Route route : handlers) {
            if (route.match(context)) {
                return Optional.of(route.getHandler());
            }
        }
        return Optional.empty();
    }
}
