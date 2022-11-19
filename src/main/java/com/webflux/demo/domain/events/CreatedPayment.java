package com.webflux.demo.domain.events;

import com.webflux.demo.core.messages.Event;
import com.webflux.demo.domain.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreatedPayment extends Event {
    private UUID id;
    private String userId;
    private PaymentStatus status;
}
