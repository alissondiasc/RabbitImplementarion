package com.rabbit.consumer.listener;
import com.rabbit.consumer.broker.Processor;
import com.rabbit.consumer.event.ConsumerEvent;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

public interface ConsumerListener {
    void listenerMenssage(Message<ConsumerEvent> message);
}
