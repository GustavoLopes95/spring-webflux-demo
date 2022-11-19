package com.webflux.demo.domain.services;

import com.webflux.demo.application.models.viewmodels.CreatedPaymentViewModel;
import com.webflux.demo.domain.entities.Payment;
import com.webflux.demo.domain.repositories.IPaymentRepository;
import com.webflux.demo.domain.services.interfaces.IPaymentService;
import com.webflux.demo.infra.publisher.PaymentPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Service
public class PaymentService implements IPaymentService {

    private final IPaymentRepository repository;
    private final PaymentPublisher publisher;

    public PaymentService(final IPaymentRepository repository, final PaymentPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    @Override
    public Mono<CreatedPaymentViewModel> createPayment(final String userId) {
        final var payment = new Payment();
        payment.create(userId);
        return this.repository.createPayment(payment)
                .flatMap(next -> this.publisher.onPaymentCreate(payment))
                .flatMap(next -> {
                    log.info("Convert for view model");
                    return Mono.fromCallable(() -> new CreatedPaymentViewModel(
                            next.getId(),
                            next.getUserId(),
                            next.getStatus()
                    ));
                });
    }

    @Override
    public Mono<Payment> approvedPayment(final String key) {
            return this.repository.getPayment(key)
                .flatMap(payment -> {
                    log.info("Try to approved payment {}", payment.getUserId());
                    payment.approved();
                    log.info("Payment {} approved", payment.getUserId());
                    return this.repository.updatePayment(payment);
                })
                    .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Payment> getPayment(final String key) {
        return this.repository.getPayment(key);
    }
}
