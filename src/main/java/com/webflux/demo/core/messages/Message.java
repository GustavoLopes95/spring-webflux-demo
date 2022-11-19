package com.webflux.demo.core.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public abstract class Message {

    private UUID id;
    private String messageType;

    public Message() {
        this.id = UUID.randomUUID();
        this.messageType = this.getClass().getSimpleName();
    }
}
