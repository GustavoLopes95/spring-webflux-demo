package com.webflux.demo.configurations;

import com.webflux.demo.domain.events.CreatedPayment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Sinks;

@Configuration
public class SinkConfiguration {

    @Bean
    public Sinks.Many<CreatedPayment> sink() {
        return Sinks.many().multicast()
                .onBackpressureBuffer(1000);
    }
}
