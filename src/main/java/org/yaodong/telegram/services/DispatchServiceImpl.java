package org.yaodong.telegram.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.yaodong.telegram.configuration.Bot;
import org.yaodong.telegram.configuration.Routing;
import org.yaodong.telegram.dispatch.Context;
import org.yaodong.telegram.dispatch.Route;
import org.yaodong.telegram.entities.Profile;
import org.yaodong.telegram.handlers.Handler;

import java.util.List;
import java.util.Optional;

@Service
public class DispatchServiceImpl implements DispatchService {

    private ProfileService profileService;

    private List<Route> routes;

    @Autowired
    public DispatchServiceImpl(ProfileService profileService, Routing routing) {
        this.routes = routing.routes();
        this.profileService = profileService;
    }

    @Override
    public void route(Update update, Bot bot) {
        Profile profile = profileService.fetch(update);
        Context context = new Context(update, profile, bot);
        findHandler(context).ifPresent((h) -> h.handle(context));
    }

    private Optional<Handler> findHandler(Context context) {
        for (Route route : routes) {
            if (route.match(context)) {
                return Optional.of(route.getHandler());
            }
        }
        return Optional.empty();
    }
}
