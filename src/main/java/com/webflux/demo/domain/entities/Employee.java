package com.webflux.demo.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class Employee implements Serializable {

    private final static Long SerialVersionUID = -1L;

    private UUID id;

    private String name;

    public void criar(
            final String name
    ) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public void popular(
            final UUID id,
            final String name
    ) {
        this.id = id;
        this.name = name;
    }
}
