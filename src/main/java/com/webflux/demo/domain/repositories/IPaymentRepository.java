package com.webflux.demo.domain.repositories;

import com.webflux.demo.domain.entities.Payment;
import reactor.core.publisher.Mono;

public interface IPaymentRepository {

    Mono<Payment> createPayment(final Payment payment);

    Mono<Payment> updatePayment(final Payment payment);

    Mono<Payment> getPayment(final String key);

}
