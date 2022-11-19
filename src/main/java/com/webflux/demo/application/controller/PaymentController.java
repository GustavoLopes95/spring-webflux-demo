package com.webflux.demo.application.controller;

import com.webflux.demo.application.models.inputmodels.CreatePaymentInputModel;
import com.webflux.demo.application.models.viewmodels.CreatedPaymentViewModel;
import com.webflux.demo.domain.entities.Payment;
import com.webflux.demo.domain.services.interfaces.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/payments")
public class PaymentController {

    private final IPaymentService service;

    public PaymentController(final IPaymentService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CreatedPaymentViewModel> createPayment(final @RequestBody CreatePaymentInputModel inputModel) {
        final var userId = inputModel.getUserId();
        log.info("Payment to be processed {}", userId);
        return this.service.createPayment(userId);
    }

    @GetMapping(value = "/{id}")
    public Mono<Payment> getPayment(final @PathVariable("id") UUID id) {
        return this.service.getPayment(id.toString());
    }
}
