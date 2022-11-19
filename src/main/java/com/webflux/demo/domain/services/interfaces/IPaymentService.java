package com.webflux.demo.domain.services.interfaces;

import com.webflux.demo.application.models.viewmodels.CreatedPaymentViewModel;
import com.webflux.demo.domain.entities.Payment;
import reactor.core.publisher.Mono;

public interface IPaymentService {

    Mono<CreatedPaymentViewModel> createPayment(final String userId);

    Mono<Payment> approvedPayment(final String key);

    Mono<Payment> getPayment(final String key);
}
