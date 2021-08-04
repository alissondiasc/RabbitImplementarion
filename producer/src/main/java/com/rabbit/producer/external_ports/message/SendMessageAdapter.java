package com.rabbit.producer.external_ports.message;

import com.rabbit.producer.commons.external_ports.message.MessagePublisher;
import com.rabbit.producer.commons.external_ports.message.SuccessMessage;
import com.rabbit.producer.config.broker.Source;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableBinding({Source.class})
@RequiredArgsConstructor
public class SendMessageAdapter implements MessagePublisher {

    private final Source output;

    @Override
    public <T extends SuccessMessage> void sendSuccessMessage(T message) {
        log.info("Producer Message Event - Sending message {} to queue", message.getEventId());
        try {
            this.output.commonChannel().send(MessageBuilder.withPayload(message).build());

            log.info("Producer Message Event - Published message {} to queue", message.getEventId());
        } catch (IllegalArgumentException e) {
            log.error("Producer Message Event - Error serializing message: {}", e.getMessage(), e);
        }
    }
}

