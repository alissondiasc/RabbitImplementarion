package com.rabbit.consumer.broker;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Source {
    String OUTPUT = "consumerMenssageChannelDeadLetterChannel";

    @Output(OUTPUT)
    MessageChannel consumerDlq();

}