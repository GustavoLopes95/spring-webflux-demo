package com.webflux.demo.application.listenners;

import com.webflux.demo.domain.events.CreatedPayment;
import com.webflux.demo.domain.services.interfaces.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Slf4j
@Component
public class PaymentListenner implements InitializingBean {

    private final Sinks.Many<CreatedPayment> sink;
    private final IPaymentService service;

    public PaymentListenner(final Sinks.Many<CreatedPayment> sink, final IPaymentService service) {
        this.sink = sink;
        this.service = service;
    }

    @Override
    public void afterPropertiesSet() {
        this.sink.asFlux()
                .subscribe(
                        next ->  {
                            log.info("On pubsub next message - {}", next.getId());
                            this.service.approvedPayment(next.getId().toString())
                                    .doOnNext(it -> log.info("Payment {} approved!!!", it.getId()))
                                    .subscribe();
                        },
                        error -> {
                            log.info("On pubsub error - {}", error);
                        },
                        () -> {
                            log.info("On pubsub complete - {}");
                        }
                );
    }
}
