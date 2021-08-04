package com.rabbit.consumer.listener.impl;

import com.rabbit.consumer.broker.Processor;
import com.rabbit.consumer.broker.Sink;
import com.rabbit.consumer.broker.Source;
import com.rabbit.consumer.event.ConsumerEvent;
import com.rabbit.consumer.listener.BasicListenerHandler;
import com.rabbit.consumer.listener.ConsumerListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@EnableBinding({Sink.class, Source.class})
@Slf4j
@Component
public class ConsumerListenerImpl extends BasicListenerHandler implements ConsumerListener {

    private final Source output;

    @Autowired
    public ConsumerListenerImpl(
            Source output) {
        this.output = output;
    }

    @StreamListener(Processor.INPUT)
    @Override
    public void listenerMenssage(Message<ConsumerEvent> message) {
        log.info("Consumer Request - Listen event");

        super.verifyMessageAttemptsConsumptions(message, this.output.consumerDlq(),
                message.getPayload().getId());

        log.info("String id of menssage received:" + message.getPayload().getId());
        log.info("String body menssage received:" + message.getPayload().getMsg());
    }

    //Listener com erro para teste. Repetir 3 vezes com um intervalo de 6 minutos caso não consiga execultar enviar para DLQ.
    /*@StreamListener(Processor.INPUT)
    @Override
    public void listenerMenssage(Message<ConsumerEvent> message) {
        log.info("Consumer Request - Listen event");

        super.verifyMessageAttemptsConsumptions(message, this.output.consumerDlq(),
                message.getPayload().getId());
        message = null;

        log.info("String id of menssage received:" + message.getPayload().getId());
        log.info("String body menssage received:" + message.getPayload().getMsg());
    }

     */
}
