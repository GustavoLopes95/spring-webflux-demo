package com.webflux.demo.domain.entities;

import com.webflux.demo.domain.enums.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class Payment implements Serializable {

    private static final long SerialVersionUID = -1L;

    private UUID id;

    private String userId;

    private PaymentStatus status;

    private Instant approvedDate;

    public void create(
            final String userId
    ) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.status = PaymentStatus.PENDING;
    }

    public void approved() {
        this.status = PaymentStatus.APPROVED;
        this.approvedDate = Instant.now();
    }
}
