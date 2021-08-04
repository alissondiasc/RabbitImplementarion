package com.rabbit.producer.commons.external_ports.message;


public interface MessagePublisher {
    <T extends SuccessMessage> void sendSuccessMessage(T message);
}
