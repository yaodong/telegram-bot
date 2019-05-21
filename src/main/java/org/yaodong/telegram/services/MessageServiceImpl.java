package org.yaodong.telegram.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.yaodong.telegram.aws.SimpleQueue;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.SendMessageBatchRequestEntry;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private SimpleQueue client;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public MessageServiceImpl(SimpleQueue client) {
        this.client = client;
    }

    @Override
    public void enqueue(List<Update> updates) {
        List<SendMessageBatchRequestEntry> entries = updates.stream()
                .map(this::mapUpdateToRequestEntry).filter(Objects::nonNull).collect(Collectors.toList());

        if (!entries.isEmpty()) {
            client.sendMessageBatch(entries);
        }
    }

    @Override
    public List<Update> dequeue() {
        return client.receiveMessages().stream()
                .map(this::mapMessageToUpdate).filter(Objects::nonNull).collect(Collectors.toList());
    }

    private SendMessageBatchRequestEntry mapUpdateToRequestEntry(Update update) {
        try {
            String updateString = objectMapper.writeValueAsString(update);
            return SendMessageBatchRequestEntry.builder()
                    .id(update.getUpdateId().toString())
                    .messageGroupId("updates")
                    .messageDeduplicationId(update.getUpdateId().toString())
                    .messageBody(updateString)
                    .build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Update mapMessageToUpdate(Message message) {
        try {
            Update update = objectMapper.readValue(message.body(), Update.class);
            client.deleteMessage(message);
            return update;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
