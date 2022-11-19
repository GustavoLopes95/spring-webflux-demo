package com.webflux.demo.infra.data.repositories;

import com.webflux.demo.domain.entities.Payment;
import com.webflux.demo.domain.repositories.IPaymentRepository;
import com.webflux.demo.infra.data.InMemoryDatabase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Repository
public class PaymentRepositoryInMemory extends InMemoryDatabase<Payment> implements IPaymentRepository {


    @Override
    public Mono<Payment> createPayment(final Payment payment) {
        return Mono.fromCallable(() -> {
            log.info("Saving payment transaction for user {}", payment.getUserId());
            return this.save(payment.getId().toString(), payment);
        })
            .subscribeOn(Schedulers.boundedElastic())
            .doOnNext(next -> log.info("Payment received {}", next.getId()));
    }

    @Override
    public Mono<Payment> updatePayment(final Payment payment) {
        return Mono.fromCallable(() -> this.save(payment.getId().toString(), payment));
    }


    public Mono<Payment> getPayment(final String key) {
        return Mono.fromCallable(() -> {
            return  this.get(key);
        })
            .subscribeOn(Schedulers.boundedElastic());
    }
}
