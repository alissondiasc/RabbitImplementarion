package com.rabbit.producer.events;

import com.rabbit.producer.commons.external_ports.message.SuccessMessage;
import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class MenssageSendExampleEvent implements SuccessMessage {

    private String id;
    private String msg;


    @Override
    public String getEventId() {
        return id;
    }
}
