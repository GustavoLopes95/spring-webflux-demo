package com.webflux.demo.core.messages;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public abstract class Event extends Message {

    private Instant createdAt;

    public Event() {
        super();
        this.createdAt = Instant.now();
    }

    public Event(final UUID id, final String messageType, final Instant createdAt) {
        super(id, messageType);
        this.createdAt = createdAt;
    }
}
