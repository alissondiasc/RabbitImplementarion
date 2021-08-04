package com.rabbit.consumer.broker;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Sink {
    String INPUT = "consumerMenssageChannel";

    @Input(INPUT)
    SubscribableChannel consumerMenssageChannel();

}