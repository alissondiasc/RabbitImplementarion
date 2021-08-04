package com.rabbit.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.ImmediateAcknowledgeAmqpException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
public abstract class BasicListenerHandler {
    @Value("${app.rabbitmq.max-retry:3}")
    private Long retries;

    protected <T> void verifyMessageAttemptsConsumptions(Message<T> message, MessageChannel messageChannel, String id) {
        Long count = getDeathLetterCount(message.getHeaders());
        log.warn("Count for retry: "+count);
        if (count >= retries) {
            processDeadLetter(message, messageChannel, id);
        }
    }

    private Long getDeathLetterCount(MessageHeaders headers) {
        if (Objects.isNull(headers.get("x-death"))) {
            return 0L;
        }

        try {
            //noinspection unchecked
            List<Map<String, ?>> deathHeader = (List<Map<String, ?>>) headers.get("x-death");
            //noinspection ConstantConditions
            return (Long) deathHeader.get(0).get("count");
        } catch (ClassCastException | NullPointerException e) {
            return 0L;
        }
    }

    private <T> void processDeadLetter(Message<T> message, MessageChannel deadLetterChannel, String id) {
        String name = message.getPayload().getClass().getSimpleName();
        log.warn("{} - Sending message id {} to dlq after {} attempts", name, id, retries);
        deadLetterChannel.send(message);
        throw new ImmediateAcknowledgeAmqpException(String.format("%s - Failed after %s attempts", name, retries));
    }
}
