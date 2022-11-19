package com.webflux.demo.infra.publisher;

import com.webflux.demo.domain.entities.Payment;
import com.webflux.demo.domain.events.CreatedPayment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Component
public class PaymentPublisher {

    private final Sinks.Many<CreatedPayment> sink;

    public PaymentPublisher(final Sinks.Many<CreatedPayment> sink) {
        this.sink = sink;
    }

    public Mono<Payment> onPaymentCreate(final Payment payment) {
        return Mono.fromCallable(() -> {
            log.info("Publish created payment event for payment {}", payment.getId());
            return new CreatedPayment(payment.getId(), payment.getUserId(), payment.getStatus());
        })
                .subscribeOn(Schedulers.parallel())
                .doOnNext(message -> this.sink.tryEmitNext(message))
                .thenReturn(payment);
    }
}
