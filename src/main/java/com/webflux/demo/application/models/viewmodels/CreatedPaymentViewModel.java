package com.webflux.demo.application.models.viewmodels;

import com.webflux.demo.domain.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreatedPaymentViewModel {

    private UUID id;
    private String userId;
    private PaymentStatus status;
}
