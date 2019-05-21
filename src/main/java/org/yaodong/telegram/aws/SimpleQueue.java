package org.yaodong.telegram.aws;

import org.springframework.stereotype.Component;
import org.yaodong.telegram.services.SettingService;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.Collection;
import java.util.List;

@Component
public class SimpleQueue {

    private SqsClient client;

    private String messageQueueUrl;

    public SimpleQueue(SettingService settingService) {
        this.client = SqsClient.create();
        this.messageQueueUrl = settingService.getString("AWS_SQS_QUEUE_URL");
    }

    public void sendMessageBatch(Collection<SendMessageBatchRequestEntry> requestEntries) {
        SendMessageBatchRequest request = SendMessageBatchRequest.builder()
                .queueUrl(messageQueueUrl)
                .entries(requestEntries)
                .build();

        client.sendMessageBatch(request);
    }

    public List<Message> receiveMessages() {
        ReceiveMessageRequest request = ReceiveMessageRequest.builder()
                .queueUrl(messageQueueUrl)
                .maxNumberOfMessages(5)
                .build();

        return client.receiveMessage(request).messages();
    }

    public void deleteMessage(Message message) {
        DeleteMessageRequest request = DeleteMessageRequest.builder()
                .queueUrl(messageQueueUrl)
                .receiptHandle(message.receiptHandle())
                .build();

        client.deleteMessage(request);
    }
}
