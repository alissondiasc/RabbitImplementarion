package com.rabbit.consumer.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Objects;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsumerEvent {

    private String id;
    private String msg;

    @Getter(AccessLevel.NONE)
    private String eventId;

    public String getId() {
        if (Objects.isNull(eventId)) {
            return DigestUtils.sha1Hex(this.toString());
        }
        return eventId;
    }
}

