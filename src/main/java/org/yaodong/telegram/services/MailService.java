package org.yaodong.telegram.services;

public interface MailService {

    void send(String to, String subject, String body);
}

