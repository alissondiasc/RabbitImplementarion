package com.rabbit.producer.config.broker;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Source {

    String OUTPUT = "producerMenssage";

    @Output(OUTPUT)
    MessageChannel commonChannel();

}