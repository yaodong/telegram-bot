package org.yaodong.telegram.services;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.yaodong.telegram.entities.Profile;

public interface ProfileService {

    Profile fetch(Update update);
}
