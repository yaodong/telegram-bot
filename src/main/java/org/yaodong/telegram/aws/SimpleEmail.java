package org.yaodong.telegram.aws;

import org.springframework.stereotype.Component;
import org.yaodong.telegram.services.SettingService;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

@Component
public class SimpleEmail {

    private SesClient client;

    private String fromAddress;

    public SimpleEmail(SettingService settingService) {
        this.client = SesClient.create();
        this.fromAddress = settingService.getString("AWS_SES_FROM_ADDRESS");
    }

    public void send(SendEmailRequest request) {
        try {
            client.sendEmail(request);
        } catch (SdkException e) {
            System.err.println("There was an error during sending email: " + e.getMessage());
        }
    }

    public boolean send(String toAddress, String subject, String body) {
        Destination destination = Destination.builder().toAddresses(toAddress).build();

        Content subjectText = Content.builder().charset("UTF-8").data(subject).build();
        Content bodyText = Content.builder().charset("UTF-8").data(body).build();

        Body mailBody = Body.builder().text(bodyText).build();

        Message message = Message.builder().subject(subjectText).body(mailBody).build();

        SendEmailRequest request = SendEmailRequest.builder()
                .source(fromAddress)
                .destination(destination)
                .message(message)
                .build();

        try {
            client.sendEmail(request);
        } catch (SdkException e) {
            System.err.println("There was an error during sending email: " + e.getMessage());
            return false;
        }

        return true;
    }
}
