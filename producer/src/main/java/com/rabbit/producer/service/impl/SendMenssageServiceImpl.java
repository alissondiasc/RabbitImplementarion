package com.rabbit.producer.service.impl;

import com.rabbit.producer.commons.external_ports.message.MessagePublisher;
import com.rabbit.producer.events.MenssageSendExampleEvent;
import com.rabbit.producer.service.BatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendMenssageServiceImpl implements BatchService {
    private final MessagePublisher publisher;

    public SendMenssageServiceImpl(@Qualifier("sendMessageAdapter")MessagePublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void process() {
        MenssageSendExampleEvent event = MenssageSendExampleEvent.builder().id("1").msg("Test menssage Rabbit").build();
        try {
            publisher.sendSuccessMessage(event);
        } catch (Exception e) {
            log.error("Erro ao enviar para product-batch-adapter productId: {}", event.getId(), e.getMessage());
        }
    }
}
