package org.yaodong.telegram.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaodong.telegram.aws.SimpleEmail;


@Service
public class MailServiceImpl implements MailService {

    private SimpleEmail client;

    @Autowired
    public MailServiceImpl(SimpleEmail client) {
        this.client = client;
    }

    @Override
    public void send(String to, String subject, String body) {
        this.client.send(to, subject, body);
    }

}
